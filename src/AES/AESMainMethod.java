package AES;

public class AESMainMethod {
	public static void main(String a[]) {
		AES aes = new AES();
		String key = "00000000000000000000000000000000";
		String line = "54776F204F6E65204E696E652054776F";
		int[][] myKeys = aes.keySchedule(key);
		System.out.println("keys are the following: ");
		AES.MatrixToString(myKeys);

		int[][] keymatrix = aes.keySchedule(key);
		int numRounds = 10 + (((key.length() * 4 - 128) / 32));
		int[][] state, initvector = new int[4][4];
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
		AES.MatrixToString(state);
	}
}
