public class Complex_numbers {
    private double a,b; // complex number = a + bi

    //конструкторы
    public Complex_numbers ()
    {
        this (0,0);
    }
    public Complex_numbers (double a, double b)
    {
        this.a = a;
        this.b = b;
    }
    public Complex_numbers (double a) // воспринимаем как целое число
    {
        this (a,0);
    }

    //сетеры и гетеры
    public void setA (double a){
        this.a = a;
    }
    public void setB (double b){
        this.b = b;
    }
    public double getA (){
        return this.a;
    }
    public double getB (){
        return this.b;
    }

    //функции для операций с комплексными числами
    public Complex_numbers addition (Complex_numbers c)
    {
        return new Complex_numbers (this.a + c.a, this.b + c.b);
    }
    public Complex_numbers subtraction (Complex_numbers c)
    {
        return new Complex_numbers (this.a - c.a, this.b - c.b);
    }
    public Complex_numbers multiplication (Complex_numbers x)
    {
        return new Complex_numbers(this.a * x.a - this.b * x.b, this.a * x.b + this.b * x.a );
        //(a + bi) * (c +di) = (ac - bd) + (ad + bc)*i
    }
    public Complex_numbers division (Complex_numbers x)
    {
        if ((x.a == 0) && (x.b == 0)) // проверка на то, что при делении комплексных чисел мы не получаем 0 в знаменателе
        {
            System.out.print("It is impossible to divide");
            System.exit(0);
        }
        double del = x.a*x.a + x.b*x.b;
        return new Complex_numbers ((this.a * x.a + this.b * x.b)/del, (this.b * x.a - this.a * x.b)/del);
    }

    //функция печати комплексного числа
    public void print_k ()
    {
        System.out.printf("%f", a);
        if (b >= 0)
        {
            System.out.print(" + ");
            System.out.printf("%f", b);
        }
        else
        {
            System.out.print(" - ");
            System.out.printf("%f", -b);
        }
        System.out.print ("i  ");
    }
}
