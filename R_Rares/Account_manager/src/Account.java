public class Account {
    private String username;
    private String email;
    private String password;

    public Account(String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUsername(){
        return username;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public boolean verifyPassword(String password){
        boolean correct_length = false;
        boolean has_letter = false;
        boolean has_number = false;
        boolean has_uppercase = false;
        boolean has_lowercase = false;
        boolean has_special_char = false;

        if(password.length() > 8 && password.length() < 20){
            correct_length = true;
        }
        for(int i = 0; i < password.length();i++){
            if(Character.isDigit(password.charAt(i))){
                has_number = true;
            }
            if(Character.isAlphabetic(password.charAt(i))){
                has_letter = true;
            }
            if(Character.isUpperCase(password.charAt(i))){
                has_uppercase = true;
            }
            if(Character.isLowerCase(password.charAt(i))){
                has_lowercase = true;
            }
            if(!Character.isLetterOrDigit(password.charAt(i)) && !Character.isWhitespace(password.charAt(i))){
                has_special_char = true;
            }
        }
        if(correct_length && has_letter && has_number && has_uppercase && has_lowercase && has_special_char){
            return true;
        }
        else{
            System.out.println("Password condition not respected");
            return false;
        }
    }
}
