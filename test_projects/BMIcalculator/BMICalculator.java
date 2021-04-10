public class BMICalculator {
    private double weight, height, BMI;
    
    public BMICalculator( double weight, double height) {
        this.weight = weight;
        this.height = height;
    }

    public void calculateBMI() {
        BMI = weight / (height * height);
    }
    
    public boolean isOverweight() {
        return (BMI > 25);
    }

    //this is a commment in java 
    //below will our main method come 

    public static void main(String[] args) {
        BMICalculator calculator = new BMICalculator(60, 1.70);
        double bmi = calculator.calculateBMI();
        
        //print out the bmi

        system.out.println('Your BMI is ' + bmi + '.')
    }
}

