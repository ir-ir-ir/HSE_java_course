public class Matrix {
    private int m, n; //  m - количество строк, n - количество столбцов
    private Complex_numbers [][] matrix; // двумерный массив с матрицей

    //геттеры и сеттеры
    public void setRows(int m) {
        this.m = m;
    }
    public void setCols(int n) {
        this.n = n;
    }
    public void setMatrix(Complex_numbers[][] matrix) {
        this.matrix = new Complex_numbers[m][n];
        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                this.matrix[i][j] = matrix[i][j];
            }
        }
    }
    public int getRows() {
        return m;
    }
    public int getCols() {
        return n;
    }
    public Complex_numbers[][] getMatrix() {
        return matrix;
    }
    public Complex_numbers getMatrix_ij (int i, int j) //получение комплексного числа по индексу
    {
        return matrix[i][j];
    }

    //конструкторы
    Matrix ()
    {
        m = 0; n = 0;
        System.out.print("Matrix cannot be created");
        System.exit(0);
    }
    Matrix (int m, int n, Complex_numbers [][] matrix)
    {
        this.m = m;
        this.n = n;
        this.matrix = new Complex_numbers[m][n];
        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                this.matrix[i][j] = matrix[i][j];
            }
        }
    }

    //печать матрицы
    public void print_m()
    {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Complex_numbers x = getMatrix_ij(i, j);
                x.print_k();
            }
            System.out.print("\n");
        }
        System.out.print("\n\n");
    }

    // операции над матрицами
    public Matrix addition_m (Matrix a)
    {
        if ((m != a.getRows()) || (n != a.getCols())) // проверка на то, что матрицы одинакового размера
        {
            System.out.print ("Matrices of different sizes");
            System.exit(0);
        }
        //создание массива для матрицы-ответа
        Complex_numbers[][] arr = new Complex_numbers[m][n];

        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                arr[i][j] = matrix[i][j].addition(a.getMatrix_ij (i,j));
            }
        }

        Matrix answer = new Matrix (m, n, arr);
        answer.print_m();
        return answer;
    }
    public Matrix subtraction_m (Matrix a)
    {
        if ((m != a.getRows()) || (n != a.getCols())) // проверка на то, что матрицы одинакового размера
        {
            System.out.print ("Matrices of different sizes");
            System.exit(0);
        }
        //создание массива для матрицы-ответа
        Complex_numbers[][] arr = new Complex_numbers[m][n];

        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                arr[i][j] = matrix[i][j].subtraction(a.getMatrix_ij (i,j));
            }
        }

        Matrix answer = new Matrix (m, n, arr);
        answer.print_m();
        return answer;
    }
    public Matrix multiplication_m (Matrix a)
    {
        if (n != a.getRows())  // проверка на допустимый размер матриц
        {
            System.out.print ("The matrices have an incorrect size");
            System.exit(0);
        }
        //создание массива для матрицы-ответа
        Complex_numbers[][] arr = new Complex_numbers[m][a.getCols()];

        int n2 = a.getCols();
        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n2; j++)
            {
                arr[i][j] = new Complex_numbers(0,0);
                for (int e = 0; e < n; e++)
                {
                    Complex_numbers prom = matrix[i][e].multiplication(a.getMatrix_ij(e, j));
                    arr[i][j] = arr[i][j].addition(prom);
                }
            }
        }
        Matrix answer = new Matrix (m, n2, arr);
        answer.print_m();
        return answer;
    }
    public Matrix T()
    {
        Complex_numbers[][] arr = new Complex_numbers[n][m];
        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                arr[i][j] = matrix[j][i];
            }
        }
        Matrix answer = new Matrix (n, m, arr);
        answer.print_m();
        return answer;
    }
}
