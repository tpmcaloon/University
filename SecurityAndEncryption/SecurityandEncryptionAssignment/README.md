#RSA Encryption

This is a Java program that demonstrates RSA encryption. It allows users to generate public and private keys, encrypt a message using the public key, and decrypt the message using the private key.

##Installation
To use this program, clone this repository or download the source code files.

##Usage
Compile and run the RSA.java file to launch the program. The program will display a window with several input fields and buttons.

##Generate Keys
Click the Generate Keys button to generate a public and private key pair. The program will display the keys in their respective input fields.

##Encrypt a Message
Type a message into the Message (Alice) text area and click the Encrypt (Send to Bob) button to encrypt the message using the public key. The encrypted message will be displayed in the Encrypted Message input field.

##Decrypt a Message
Copy the encrypted message from the Encrypted Message input field and paste it into the Intercepted Message (Charlie) input field. Click the Decrypt (Bob Receives the Message) button to decrypt the message using the private key. The decrypted message will be displayed in the Decrypted Message (Bob) text area.

Note: The program assumes that the messages are text-based and uses the getBytes() method to convert them to BigInteger objects. This may not work for messages that contain non-text data.

##License
This program is licensed under the MIT License. See the LICENSE file for more information.