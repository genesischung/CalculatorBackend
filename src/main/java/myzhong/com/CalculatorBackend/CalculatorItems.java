package myzhong.com.CalculatorBackend;

public class CalculatorItems {
    private String display;
    private boolean plus;
    private boolean subtract;
    private boolean multiply;
    private boolean divide;

    public CalculatorItems(String display, boolean plus, boolean subtract,
                      boolean multiply, boolean divide){
        this.display = display;
        this.plus = plus;
        this.subtract = subtract;
        this.multiply = multiply;
        this.divide = divide;
    }
}
