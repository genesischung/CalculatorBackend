package myzhong.com.CalculatorBackend;

public class Calculator {
    private String previousNum = "0";
    private String currentNum = "0";
    private String currentDisplay = "0";
    private boolean dotClicked = false;
    private boolean equalClicked = false;
    private char operator = 0;
    private double lastOperand = 0;

    private boolean plus = false;
    private boolean subtract = false;
    private boolean multiply = false;
    private boolean divide = false;


    public String getCurrentNum() {
        return currentNum;
    }

    public CalculatorItems processInput(String inputs){
        char input = inputs.charAt(0);
        switch(input){
            case '1': case '2': case '3': case '4': case '5':
            case '6': case '7': case '8': case '9':
                clickNumber(Integer.parseInt(String.valueOf(input)));
                System.out.println("number: " + input);
                break;
            case '.':
                clickDot();
                System.out.println("dot");
                break;
            case 'p': case 's': case 'm': case 'd':
                clickOperator(input);
                System.out.println("operator: " + input);
                break;
            case 'e':
                clickEqual(true);
                System.out.println("equal");
                break;
            case 'c':
                clickAC();
                System.out.println("AC");
                break;
        }
        return new CalculatorItems(currentDisplay, plus, subtract, multiply, divide);
    }

    /**
     * Take action when a number button is pressed
     * if last action is equal, clear for a new calculation
     * @param num the number button being pressed
     */
    public void clickNumber(int num){
        if(equalClicked){
            clickAC();
            //System.out.println("AC");
        }
        if(currentNum.equals("0")){
            currentNum = String.valueOf(num);
        }
        else {
            currentNum = currentNum + String.valueOf(num);
        }
        updateDisplay();
    }

    /**
     * Add a decimal dot to the current number
     * ignore the input if dot has been clicked once
     */
    public void clickDot(){
        // ignore if dot has been clicked once
        if(dotClicked){
        }
        else{
            currentNum = currentNum + ".";
            dotClicked = true;
            updateDisplay();
        }
    }

    /**
     * Take action when a operator button is pressed
     * if last action is equal, perform operation on the last result
     * if last action is a operator, evaluate the saved operation and perform
     * further operation on the result
     * @param operator the operator button pressed
     */
    public void clickOperator(char operator){
        // Only evaluate if the last action is not equal
        if(!equalClicked){
            clickEqual(false);
        }
        equalClicked = false;

        // save the current number and clear the display for new input
        previousNum = currentNum;
        currentNum = "0";
        dotClicked = false;

        // save the selected operator
        this.operator = operator;

        switch(operator){
            case 'p':
                resetOperators();
                plus = true;
                break;
            case 's':
                resetOperators();
                subtract = true;
                break;
            case 'm':
                resetOperators();
                multiply = true;
                break;
            case 'd':
                resetOperators();
                divide = true;
                break;
            default:
                /*Toast.makeText(getActivity(), "invalid operation",
                        Toast.LENGTH_SHORT).show();*/
                return;
        }

    }

    /**
     * Evaluate the operation when equal or a operator button is pressed
     * @param equalButton true if called by the equal button
     */
    public void clickEqual(boolean equalButton){
        double result = Double.valueOf(previousNum.replaceAll("\\.+$", ""));
        double operand = Double.valueOf(currentNum.replaceAll("\\.+$", ""));

        // if equal is pressed multiple times, repeat the last operation
        if(equalClicked){
            result = operand;
            operand = lastOperand;
        }
        lastOperand = operand;

        //System.out.println("result： " + result + "operand: " + operand);

        switch(operator){
            case 'p':
                result += operand;
                break;
            case 's':
                result -= operand;
                break;
            case 'm':
                result *= operand;
                break;
            case 'd':
                result /= operand;
                break;
            default:
                /*Toast.makeText(getActivity(), "invalid operation",
                        Toast.LENGTH_SHORT).show();*/
                return;
        }


        // Do not show decimals if the result is a whole number
        if(Math.floor(result) == result) {
            currentNum = String.valueOf((int)result);
        }
        else{
            currentNum = String.valueOf(result);
        }

        // do not set equalClicked if the method is called by other buttons
        if(equalButton) {
            equalClicked = true;
        }
        updateDisplay();
    }

    /**
     * reset all member variables back to default
     */
    public void clickAC(){
        previousNum = "0";
        currentNum = "0";
        dotClicked = false;
        equalClicked = false;
        operator = 0;
        lastOperand = 0;
        resetOperators();
        updateDisplay();
    }

    /**
     * Reset all operator buttons back to original background color
     */
    private void resetOperators(){
        plus = false;
        subtract = false;
        multiply = false;
        divide = false;
    }

    private void updateDisplay(){
        currentDisplay = currentNum;
    }


}
