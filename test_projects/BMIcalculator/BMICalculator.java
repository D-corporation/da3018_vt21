public class BMICalculator {
    private double weight, height, BMI;
    
    public BMICalculator( double weight, double height) {
        this.weight = weight;
        this.height = height;
    }

    public double calculateBMI() {
        BMI = weight / (height * height);
        return (BMI);
    }

    //this is a commment in java 
    //below will our main method come 

    public static void main(String[] args) {
        BMICalculator calculator = new BMICalculator(60, 1.70);
        double bmi = calculator.calculateBMI();
        
        //print out the bmi

        System.out.println("Your BMI is " + bmi + ".");
    }
}

