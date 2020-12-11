public class Rating{
    private int rating;
    private String userId;
    private String writingId;
    public Rating (){

    }
    public Rating (String a, String b, int c){
        userId = a;
        writingId = b;
        rating = c;
    }
    public String getUserId (){
        return userId;
    }
    public String getWritingId (){
        return writingId;
    }
    public int getRating(){
        return rating;
    }
    public void setUserId (String a){
        userId = a;
    }
    public void setWritingId (String b){
        writingId = b;
    }
    public void setRating (int c){
        rating = c;
    }
}