from socket import *
import numpy as np
import threading


serverName = '127.0.0.1'
serverPort = 12000

# Instance variables
name = ''
privacy = ''
ownPort = ''
otherPeers = [] #format [['private','john','127.0.0.1','49839]]


def UDP_CONNECTION():

    global otherPeers

   
    udpSocket = socket(AF_INET, SOCK_DGRAM) #UDP socket
    udpSocket.bind(('', int(ownPort)))  # Bind to own port for receiving messages

    def receive_messages():
        while True:
            try:
                if udpSocket:
                    message, addr = udpSocket.recvfrom(1024)                                      #Message received from other clients and their address
                    sender = next((peer for peer in otherPeers if peer[3] == str(addr[1])), None) #if sender's port number is the same as the one in the server

                    if message.decode().lower().startswith('broadcast:'):                         #if the message sent start with broadcast, send the message to all the users who are public
                        broadcast_msg = message.decode()[len('broadcast:'):].strip()              # Extract the actual broadcast message
                        print('\nBroadcast message from {}: {}'.format(sender[1], broadcast_msg))
                    else:                                                                         #else if the message doesn't start with broadcast, show from who it came from
                        print('\nReceived message from {}: {}'.format(sender[1], message.decode()))
                else:
                    # Socket is closed, exit the thread
                    break
            except OSError as e:
                if e.errno == 10038:
                    # WinError 10038: Socket operation on non-socket
                    # Socket is closed, exit the thread
                    break
                else:
                    # Handle other OSError
                    print('Error in receive_messages:', e)
                    break

    def send_messages():
        
        while True:
            try:
                if udpSocket:
                    #input the name of clinet you want to sent to, or broadcast to send to all the public clients
                    receiver_name = input("Enter the name of the client you want to chat with (or 'logoff' to exit, or 'broadcast' to send to all clients): ")

                    if receiver_name.lower() == 'logoff':                                #if the input is logoff exit the UDP socket and return to the TCP connection if connected
                        udpSocket.close()
                        print("Chat ended.")
                        break

                    message = input("Enter your message ( or 'logoff' to go offline): ") #input the message you want to send

                    if message.lower() == 'logoff':                                      #if the input is logoff exit the UDP socket and return to the TCP connection if connected
                        udpSocket.close()
                        print("Chat ended.")
                        break

                    #check for an array in the 2D array of the available clients in the server that has the same name at the inputed name of client to communicate with.
                    receiver = next((peer for peer in otherPeers if peer[1] == receiver_name or receiver_name.lower() == 'broadcast'), None)
                    if receiver:

                        if receiver_name.lower() == 'broadcast':                         #if the name entered is 'broadcast' then broadcast the message to all public clients

                            for receivers in otherPeers:
                                if receivers[0].lower()=='private':                      #now if the client if has a privacy status of private, then they wont receive the broadcast message
                                    continue
                                
                                message = 'broadcast:' + message                         # Add 'broadcast:' prefix to the message
                                receiver_ip, receiver_port = receivers[2], int(receivers[3])
                                udpSocket.sendto(message.encode(), (receiver_ip, receiver_port))
                        else:
                            receiver_ip, receiver_port = receiver[2], int(receiver[3])
                            udpSocket.sendto(message.encode(), (receiver_ip, receiver_port))
                    else:
                        print('Client not found in the list.')

                else:
                    break
            except OSError as e:
                if e.errno == 10038:
                    # WinError 10038: Socket operation on non-socket
                    # Socket is closed, exit the thread
                    break
                else:
                    # Handle other OSError
                    print('Error in send_messages:', e)
                    break

    receive_thread = threading.Thread(target=receive_messages)
    send_thread = threading.Thread(target=send_messages)

    receive_thread.start()
    send_thread.start()

    receive_thread.join()
    send_thread.join()


def TCP_CONNECTION():
    global name, privacy, ownPort, otherPeers


    # Create TCP socket for server remote port 12000
    clientSocket = socket(AF_INET, SOCK_STREAM)
    clientSocket.connect((serverName, serverPort))

    # Successfully logged in message from server
    modifiedSentence1 = clientSocket.recv(1024)
    print('From Server:', modifiedSentence1.decode())

   
    name = input("Enter your username (no white spaces!): ")
    privacy = input("Do you want to be 'PRIVATE' or 'PUBLIC': ")

    sentence = privacy + ":" + name
    clientSocket.send(sentence.encode())
    modified = clientSocket.recv(1024)
    print('From Server:', modified.decode())
    # receiving successful login message from server
    ownPort = modified.decode().split('.')[1].strip()

    
    print("\nCommands:\n"+"\n"+
          "'get_peers': returns list of all currently connected clients\n"+
            "'quit': disconnects this client from the server\n"+
            "'connect to client': user selects a client to establish a peer-to-peer connection with"+"\n")

    commands = ['get_peers','quit','connect to client']
    while sentence.lower() != 'quit':

        sentence = input('Input Command: ').lower()

        # ensure the command is valid
        while True:
            if sentence.lower().strip() not in commands:
                print("Invalid command. Please try again.")
                sentence = input('Input Command: ')
            else:
                break

          
        # for peer to peer connection to other clients
        if sentence.lower().strip() == "connect to client":
            if len(otherPeers) > 0: # testing if other clients are connected
                print("\n"+"Who would you like to connect to?")
                for i in range(len(otherPeers)):
                    # display online clients
                    print("name: {}, port: {} ,status: {},".format(
                        otherPeers[i][1], otherPeers[i][3], otherPeers[i][0]))
            
                UDP_CONNECTION() #start the UDP service
                print()
                print("welcome back to the server!!")
                print()
                continue

            else: 
                print("No other clients are connected.")
                continue


        #if sentence.lower().strip() == "go_online":
            #UDP_CONNECTION()

        clientSocket.send(sentence.encode())
        modifiedSentence = clientSocket.recv(1024)
        print("From Server:"+"\n")
        print(modifiedSentence.decode())


        # if server sends list of peers, save all peers except for (this) to list
        if sentence.lower() == "get_peers":
            otherPeers = []
            peers = modifiedSentence.decode().split('\n') 
            for peer in peers:
                peer_arr = peer.split(':') # split private:john:127.0.0.1:49839 into an array

                # check for client before saving
                if len(peer_arr) > 1 and str(peer_arr[3]) != ownPort: 
                    if len(otherPeers) >0:
                        if not (str(peer_arr[3]) in np.array(otherPeers)[:,3]): #if client is not already in our list
                            otherPeers.append(peer_arr)
                    else: 
                        otherPeers.append(peer_arr)

        

    # Close the socket when done
    clientSocket.close()

if __name__ == "__main__":
    TCP_CONNECTION()
    

