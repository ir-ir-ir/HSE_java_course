class Main
{
    public static void main (String[] args)
    {
        //пример чисел для матрицы
        Complex_numbers a = new Complex_numbers(4.1,2.3);
        Complex_numbers b = new Complex_numbers(0,0);
        Complex_numbers c = new Complex_numbers(4,2);
        Complex_numbers d = new Complex_numbers(2,1);
        Complex_numbers e = new Complex_numbers(3.1,4);
        Complex_numbers f = new Complex_numbers(5,2);
        Complex_numbers g = new Complex_numbers(1,2.1);
        Complex_numbers h = new Complex_numbers(-2,-1);
        Complex_numbers i = new Complex_numbers(-1,3);

        int m_ = 3; int n_ = 3; //размер матрицы
        Complex_numbers [][] ch_ = {{a,b,c},{d,e,f},{g,h,i}};
        Matrix matr  = new Matrix( m_, n_, ch_); //создание матрицы


        //пример чисел для второй матрицы
        Complex_numbers j = new Complex_numbers(4.1,2.12);
        Complex_numbers k = new Complex_numbers(0,12);
        Complex_numbers l = new Complex_numbers(14,1);
        Complex_numbers m = new Complex_numbers(-2,1.396);
        Complex_numbers n = new Complex_numbers(3.2141,1.7893);
        Complex_numbers o = new Complex_numbers(5.3,-4.2);
        Complex_numbers p = new Complex_numbers(2.1,2.212);
        Complex_numbers q = new Complex_numbers(-2,-1);
        Complex_numbers r = new Complex_numbers(1.3,4);

        int m__ = 3; int n__ = 3; //размер матрицы
        Complex_numbers [][] ch__ = {{j,k,l},{m,n,o},{p,q,r}};
        Matrix matr__  = new Matrix( m__, n__, ch__); //создание матрицы

        matr.addition_m(matr__);
        matr.subtraction_m(matr__);
        matr.multiplication_m(matr__);
        matr.T();
    }
}