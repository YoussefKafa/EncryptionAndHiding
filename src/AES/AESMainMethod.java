package AES;

import java.io.UnsupportedEncodingException;

import IDEA.Convert;

public class AESMainMethod {
	static int[][] state;
	public static void main(String a[]) throws UnsupportedEncodingException {
	AES aes = new AES();
		String key = "00000000000000000000000000000001";
		String line = "54776F204F6E65204E696E652054776F";
		int[][] myKeys = aes.keySchedule(key);
		System.out.println("keys are the following: ");
		System.out.println(AES.keysMatrixToString(myKeys,myKeys[0].length,myKeys.length));

		int[][] keymatrix = aes.keySchedule(key);
		int numRounds = 10 + (((key.length() * 4 - 128) / 32));
		int[][]  initvector = new int[4][4];
		state = new int[4][4];
		for (int i = 0; i < state.length; i++) // Parses line into a matrix
		{
			for (int j = 0; j < state[0].length; j++) {
				state[j][i] = Integer.parseInt(line.substring((8 * i) + (2 * j), (8 * i) + (2 * j + 2)), 16);
			}
		}
		aes.addRoundKey(state, initvector);
		aes.addRoundKey(state, aes.subKey(keymatrix, 0)); // Starts the addRoundKey with the first part of Key Expansion
		for (int i = 1; i < numRounds; i++) {

			aes.subBytes(state); // implements the Sub-Bytes subroutine.

			aes.shiftRows(state); // implements Shift-Rows subroutine.

			aes.mixColumns(state);

			aes.addRoundKey(state, aes.subKey(keymatrix, i));

		}
		aes.subBytes(state); // implements the Sub-Bytes subroutine.
		aes.shiftRows(state); // implements Shift-Rows subroutine.
		aes.addRoundKey(state, aes.subKey(keymatrix, numRounds));
		System.out.println("cipher text is : ");
		System.out.println(AES.MatrixToString(state,4,4));
		
		//Decryption
		String cipher="3FE2B2E71F3F9D96D2B68692C930BD6A";
		String decKey="00000000000000000000000000000001";
		int[][] deckeymatrix = aes.keySchedule(decKey);
		int decnumRounds = 10 + (((decKey.length() * 4 - 128) / 32));
	int[][] stated = new int[4][4];
	for (int i = 0; i < stated.length; i++) // Parses line into a matrix
	{
		for (int j = 0; j < stated[0].length; j++) {
			stated[j][i] = Integer.parseInt(cipher.substring((8 * i) + (2 * j), (8 * i) + (2 * j + 2)), 16);
		}
	}
		aes.addRoundKey(stated, aes.subKey(deckeymatrix, decnumRounds));

		for (int i = decnumRounds - 1; i > 0; i--) {

			aes.invShiftRows(stated);

			aes.invSubBytes(stated);

			aes.addRoundKey(stated, aes.subKey(deckeymatrix, i));

			aes.invMixColumns(stated);

		}

		aes.invShiftRows(stated);

		aes.invSubBytes(stated);

		aes.addRoundKey(stated, aes.subKey(deckeymatrix, 0));
		System.out.println(Convert.unHex(AES.MatrixToString(stated,4,4)));
	}
	public static int[][] getState(){
		return state;
	}
}
