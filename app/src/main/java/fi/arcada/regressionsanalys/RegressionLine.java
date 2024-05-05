package fi.arcada.regressionsanalys;

public class RegressionLine {


    private double m;
    private double k;
    private double x;
    private double correlationCoefficient;

    //konstruktör, beräknar m, k och
    public RegressionLine(double[] x, double[] y){
        double xAverage = 0;
        double yAverage = 0;
        double sumOfAllXMinusXaverage = 0;
        double sumOfAllYMinusYaverage = 0;
        double sumOfAllXTimesY = 0;
        double sumOfAllX = 0;
        double sumOfAllY = 0;
        double sumOfAllXPow = 0;
        double sumOfAllYPow = 0;
        double uppe = 0;
        double nere = 0;
        int j = 0;


        //räknar medeltalet för både x och y
        for(double i : x){
            xAverage += i;
        }
        xAverage = xAverage/x.length;

        for(double i : y){
            yAverage += i;
        }
        yAverage = yAverage/y.length;

        //beräknar vinkeln av linjen med formel för minsta kvadratmetoden och förberäder variablar som behövs i uträkningen av correlations coeffisienten
        while(j < x.length){
            sumOfAllXMinusXaverage = sumOfAllXMinusXaverage + (x[j] - xAverage);
            sumOfAllYMinusYaverage = sumOfAllYMinusYaverage + (y[j] - yAverage);
            sumOfAllXTimesY = sumOfAllXTimesY + (x[j] * y[j]);
            sumOfAllX = sumOfAllX + x[j];
            sumOfAllY = sumOfAllY + y[j];
            sumOfAllXPow = sumOfAllXPow + Math.pow(x[j], 2);
            sumOfAllYPow = sumOfAllYPow + Math.pow(y[j], 2) ;
            j++;
        }

        j = 0;

        uppe = (sumOfAllXTimesY - xAverage * yAverage * x.length);

        while(j < x.length){
            nere = nere + (Math.pow((x[j] - xAverage), 2));
            j++;
        }

        this.m = uppe / nere;

        // beräknar i vilken pungt på y axeln linjen börjar ifrån

        this.k = yAverage - (this.m * xAverage);

        // beräknar correlations coeffisientten
        double n = x.length;
        double uppe2 = n * sumOfAllXTimesY - sumOfAllX * sumOfAllY;
        double nere2 = Math.sqrt((n * sumOfAllXPow - Math.pow(sumOfAllX, 2)) * (n * sumOfAllYPow - Math.pow(sumOfAllY, 2)));
        this.correlationCoefficient = uppe2 / nere2;

    }

    //beräknar skostorläken
    public double getX(double y){
        this.x = (y - this.k) / this.m;
        return this.x;
    }

    //returnerer correlations coeffisienten
    public double getCorrelationCoefficient() {
        return this.correlationCoefficient;
    }

    public String getCorrelationGrade() {
        if (this.correlationCoefficient == 1 || this.correlationCoefficient == -1) {
            return("perfekt");
        } else if (this.correlationCoefficient >= 0.75 || this.correlationCoefficient <= -0.75) {
            return("hög");
        } else if (this.correlationCoefficient >= 0.25 || this.correlationCoefficient <= -0.25) {
            return("måttlig");
        } else if (this.correlationCoefficient > 0 || this.correlationCoefficient < 0) {
            return("låg");
        } else {
            return("ingen");
        }
    }
}
