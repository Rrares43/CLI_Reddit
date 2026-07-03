public class Comment {
    private String text;
    private String author;

    public Comment(String text,String author)
    {
        this.text=text;
        this.author=author;

    }

    //functie pentru editarea comenatriilor
    public void setText(String newText){
        this.text = newText;

    }

}
