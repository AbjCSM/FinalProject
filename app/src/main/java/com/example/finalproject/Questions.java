package com.example.finalproject;

public class Questions {
    public static class TriviaQuestion {
        private String question;
        private String[] options;
        private int correctAnswerIndex;

        public TriviaQuestion(String question, String o1, String o2, String o3, String o4, int correctAnswerIndex) {
            this.question = question;
            this.options = new String[]{o1, o2, o3, o4};
            this.correctAnswerIndex = correctAnswerIndex;
        }

        public String getQuestion() {
            return question;
        }

        public String getOption(int index) {
            return options[index];
        }

        public int getCorrectAnswerIndex() {
            return correctAnswerIndex;
        }
    }
}
