package account_manager;

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

    // checks the validity of the password
    public boolean verifyPassword(String password){
        boolean correctLength = false;
        boolean hasLetter = false;
        boolean hasNumber = false;
        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasSpecial = false;
        boolean hasWhitespace = false;

        if(password.length() >= 8 && password.length() < 40){
            correctLength = true;
        }
        for(int i = 0; i < password.length();i++){
            if(Character.isDigit(password.charAt(i))){
                hasNumber = true;
            }
            if(Character.isAlphabetic(password.charAt(i))){
                hasLetter = true;
            }
            if(Character.isUpperCase(password.charAt(i))){
                hasUppercase = true;
            }
            if(Character.isLowerCase(password.charAt(i))){
                hasLowercase = true;
            }
            if(!Character.isLetterOrDigit(password.charAt(i)) && !Character.isWhitespace(password.charAt(i))){
                hasSpecial = true;
            }
            if(Character.isWhitespace(password.charAt(i))){
                hasWhitespace = true;
            }
        }
        if(hasWhitespace){
            return false;
        }
        else if(correctLength && hasLetter && hasNumber && hasUppercase && hasLowercase && hasSpecial){
            return true;
        }
        else{
            return false;
        }
    }

    // checks the validity of the email
    public boolean verifyEmail(String email){
        if(email == null || email.isBlank()){
            return false;
        }
        int index1 = email.indexOf("@");
        int index2 = email.indexOf(".");
        if(index1 == -1 || index2 == -1 || index2 - index1 < 2){
            return false;
        }
        return true;
    }
}
