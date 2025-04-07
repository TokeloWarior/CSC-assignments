import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class OS1Assignment {
    public static void main(String[] args) {
        // Check if the correct number of arguments is provided
        if (args.length != 1) {
            System.err.println("Usage: java 0S1Assignment <input_sequence_filename>");
            return;
        }

        // Extract the filename from the command-line arguments
        String filename = args[0];

        try {
            // Call the method to read and analyze memory accesses
            String[] formattedAddresses = readAndAnalyzeMemoryAccesses(filename);
            
            Write_toFile(formattedAddresses);
            
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }

    private static void Write_toFile(String[] Format) {
        String filename = "output-OS1"; // file name
        
        try {
            // Create a FileWriter object with the specified file name
            FileWriter fileWriter = new FileWriter(filename);
            
            // Wrap the FileWriter object in a BufferedWriter for efficient writing
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (String address : Format) {
                String binaryAddress = convertToBinary(address);
                
                //System.out.println("Binary format of 0x"+address+": " + binaryAddress);
                String physical_address = Translation(binaryAddress);

                // Write content to the file
                bufferedWriter.write("0x"+physical_address+"\n");
            }
            // Close the BufferedWriter to flush any buffered content and release resources
            bufferedWriter.close();
            
            System.out.println("Data has been written to the file successfully.");
        } catch (IOException e) {
            // Handle any IOException that occurs during file writing
            e.printStackTrace();
        }
    }

    // Method to read and analyze memory accesses from the file
    public static String[] readAndAnalyzeMemoryAccesses(String filename) throws IOException {
        // Open the file input stream
        try (DataInputStream fis = new DataInputStream(new FileInputStream(filename))) {
            // Print a message indicating that memory accesses are being analyzed
            System.out.println("Analyzing memory accesses:");
            
            // Create a buffer to store formatted addresses
            StringBuilder formattedAddressesBuilder = new StringBuilder();
            
            // Loop to read the file until the end
            while (fis.available() >= 8) {
                // Convert the bytes to a long integer representing the virtual address
                long virtualAddress = fis.readLong();
                
                // Analyze the virtual address and append the formatted address to the builder
                formattedAddressesBuilder.append(analyzeVirtualAddress(virtualAddress)).append("\n");
            }
            
            // Return the formatted addresses as an array of strings
            return formattedAddressesBuilder.toString().split("\n");
        }
    }

    // Method to analyze a virtual address and format it
    public static String analyzeVirtualAddress(long virtualAddress) {
        // Format the virtual address in hexadecimal format
        return String.format("%08X", Long.reverseBytes(virtualAddress));
    }
    
    // Method to convert hexadecimal address to binary
    public static String convertToBinary(String hexAddress) {
        // Convert hexadecimal string to binary string
        String binaryAddress = Long.toBinaryString(Long.parseLong(hexAddress, 16));
        
        // Pad the binary string to ensure it has 32 bits
        binaryAddress = String.format("%32s", binaryAddress).replace(' ', '0');
        
        return binaryAddress;
    }

    public static String Translation(String VirtualAddress){
        /*
         * Physical Address=(Frame Number×Frame Size)+Offset
         * 
         * Frame Number: The number of the frame containing the desired memory page.
         * Frame Size: The size of each frame, which in our case is 128 bytes.
         * Offset: The offset within the page, indicating the specific byte within the frame
         * This formula accounts for the fact that each frame in physical memory starts at a multiple of the frame size, 
           and the offset represents the specific byte within that frame.
         */

        int[] page_table = {2,4,1,7,3,5,6};
        int size = 128;
        // Binary string
        String offset = VirtualAddress.substring(24, 32);
        String frame = VirtualAddress.substring(0, 25);
        // Convert binary string to decimal integer
        int decimal_offset = Integer.parseInt(offset, 2);
        int decimal_frame = Integer.parseInt(frame, 2);

        int Decimal_address = (page_table[decimal_frame]*size) + decimal_offset;

        String physical_address = Integer.toHexString(Decimal_address);

        //System.out.println("hexdecimal representation: " + "0x"+physical_address.toUpperCase());
        return physical_address.toUpperCase();
    }
}
