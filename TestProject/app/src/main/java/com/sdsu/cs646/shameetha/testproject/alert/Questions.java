package com.sdsu.cs646.shameetha.testproject.alert;

import java.util.ArrayList;
import java.util.Random;

public class Questions {

	enum Operator {
		ADD, SUBTRACT, MULTIPLY, DIVIDE;

		@Override
		public String toString() {
			String string = null;
			switch (ordinal()) {
			case 0:
				string = "+";
				break;
			case 1:
				string = "-";
				break;
			case 2:
				string = "*";
				break;
			case 3:
				string = "/";
				break;
			}
			return string;
		}
	}

	private ArrayList<Float> parts;
	private ArrayList<Operator> operators;
	private float answer = 0f;
	private int min = 0;
	private int max = 10;
	public Questions() {
		this(3);
	}
	
	public Questions(int numParts) {
        super();
        Random random = new Random(System.currentTimeMillis());

        parts = new ArrayList<Float>(numParts);
        for (int i = 0; i < numParts; i++)
            parts.add(i, (float) random.nextInt(max - min + 1) + min);

        operators = new ArrayList<Operator>(numParts - 1);
        for (int i = 0; i < numParts - 1; i++)
            operators.add(i, Operator.values()[random.nextInt(2) + 1]);

        ArrayList<Object> combinedParts = new ArrayList<Object>();
        for (int i = 0; i < numParts; i++) {
            combinedParts.add(parts.get(i));
            if (i < numParts - 1)
                combinedParts.add(operators.get(i));
        }

        while (combinedParts.contains(Operator.DIVIDE)) {
            int i = combinedParts.indexOf(Operator.DIVIDE);
            answer = (Float) combinedParts.get(i - 1) / (Float) combinedParts.get(i + 1);
            for (int r = 0; r < 2; r++)
                combinedParts.remove(i - 1);
            combinedParts.set(i - 1, answer);
        }
        while (combinedParts.contains(Operator.MULTIPLY)) {
            int i = combinedParts.indexOf(Operator.MULTIPLY);
            answer = (Float) combinedParts.get(i - 1) * (Float) combinedParts.get(i + 1);
            for (int r = 0; r < 2; r++)
                combinedParts.remove(i - 1);
            combinedParts.set(i - 1, answer);
        }

        while (combinedParts.contains(Operator.ADD)) {
            int i = combinedParts.indexOf(Operator.ADD);
            answer = (Float) combinedParts.get(i - 1) + (Float) combinedParts.get(i + 1);
            for (int r = 0; r < 2; r++)
                combinedParts.remove(i - 1);
            combinedParts.set(i - 1, answer);
        }
        while (combinedParts.contains(Operator.SUBTRACT)) {
            int i = combinedParts.indexOf(Operator.SUBTRACT);
            answer = (Float) combinedParts.get(i - 1) - (Float) combinedParts.get(i + 1);
            for (int r = 0; r < 2; r++)
                combinedParts.remove(i - 1);
            combinedParts.set(i - 1, answer);
        }
    }
	@Override
	public String toString() {
		StringBuilder problemBuilder = new StringBuilder();
		for (int i = 0; i < parts.size(); i++) {
			problemBuilder.append(parts.get(i));
			problemBuilder.append(" ");
			if (i < operators.size()){
				problemBuilder.append(operators.get(i).toString());
				problemBuilder.append(" ");
			}
		}
		return problemBuilder.toString();
	}

	public float getAnswer() {
		return answer;
	}

}
