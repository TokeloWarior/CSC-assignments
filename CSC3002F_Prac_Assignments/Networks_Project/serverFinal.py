from socket import *
import select

serverPort = 12000
serverSocket = socket(AF_INET, SOCK_STREAM)
serverSocket.bind(('', serverPort))
serverSocket.listen(5)
print('The server is ready to receive')

# List to keep track of connected clients
clients = [serverSocket]
C_list = []
client_info = []

# method to extract available clients from the server, and return a string of them.
def Get_clients(client_socket, list):
    clients_ports = ""
    for ports in list:
        clients_ports = clients_ports + ports[0] +":" + ports[1] + ":" + ports[2] + ":" + str(ports[3] )+"\n"
    client_socket.send(clients_ports.encode())

def store_clients(client_data,recieve_socket):
        arr = client_data.split(":")
        address = recieve_socket.getpeername()
        client_info1 = (arr[0],) + (arr[1],) + address
        client_info.append(client_info1)
        client_port = str(address[1])
        recieve_socket.send(("You registered successfully. "+client_port).encode())


if __name__ == "__main__":

    while True:
        # Use select to wait for incoming connections or data from clients
        readable, _, _ = select.select(clients, [], [])
        
        for ready_socket in readable:
            if ready_socket == serverSocket:
                # New connection, accept it
                connectionSocket, addr = serverSocket.accept()
                clients.append(connectionSocket)
                login = "successfully logged in"
                connectionSocket.send(login.encode())
                C_list.append(addr)
                print(f"New connection from {addr}")

            else:
                # Data received from a client
                client_data = ready_socket.recv(1024).decode()

                if client_data.lower() == "quit":
                    # Client wants to quit, close the connection
                    print(f"Client {ready_socket.getpeername()} has disconnected.")
                    clients.remove(ready_socket)
                    adrs = ready_socket.getpeername()
                    C_list.remove(ready_socket.getpeername())
                    
                    # Iterate over the list and remove the matching tuple
                    for tup in client_info[:]:  # Using a copy of the list to avoid modifying it while iterating
                        if (tup[2], tup[3]) == adrs:
                            client_info.remove(tup)

                    ready_socket.send("Successfully disconnected".encode())
                    ready_socket.close()

                elif client_data.lower() == "get_peers":
                    Get_clients(ready_socket,client_info)

                else:
                    #Process the received data and send a response back
                    print(f"Received from {ready_socket.getpeername()}: {client_data}")
                    
                    store_clients(client_data,ready_socket)
