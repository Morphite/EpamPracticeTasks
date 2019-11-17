package ua.nure.leonov.practice6;

public class Word {

    private String content;
    private int frequency;

    public Word(String content, int frequency) {
        this.content = content;
        this.frequency = frequency;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return "Word{" +
                "content='" + content + '\'' +
                ", frequency=" + frequency +
                '}';
    }



}
