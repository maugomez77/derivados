package mx.com.libreria.math;

public class VolatilidadImplicita {	

	public static double presentValue(double amount, double r, double T) {
		return amount * Math.exp(-r * T);
	}

	public static double putfromcall(double c, double S, double T, double K,
			double r, double d) {
		return c + presentValue(K, r, T) - presentValue(S, d, T);
	}

	public static double callfromput(double p, double S, double T, double K,
			double r, double d) {
		return p - presentValue(K, r, T) + presentValue(S, d, T);
	}

	public static double BSput(double S, double T, double k, double r, double d, double sigma) { 
		double d1, d1_numerator, d2, Nd1, Nd2, SedT, sigma_sqrtT, XerT;
		double BSput = 0;
		if (T > 0) { 
			SedT = presentValue(S, d, T);
			XerT = presentValue(k, r, T);
			sigma_sqrtT = sigma * Math.sqrt(T);

			d1_numerator = Math.log(SedT / XerT) + (0.5 * sigma * sigma * T);
			d1 = d1_numerator / sigma_sqrtT;
			d2 = d1 - sigma_sqrtT;
			
			Nd1 = Gauss.PhiStandard(-d1);
			Nd2 = Gauss.PhiStandard(-d2);
			
			BSput = XerT * Nd2 - SedT * Nd1;
			
		} else { 
			if (k < S) { 
				BSput = k - S;
			} else {
				BSput = 0;
			}
		}
		return BSput;
	}
	
	public static double BScall(double S, double T, double K, double r,
			double d, double sigma) {
		double d1, d1_numerator, d2, Nd1, Nd2, SedT, sigma_sqrtT, XerT;

		double BScall = 0;
		if (T > 0) {
			SedT = presentValue(S, d, T);
			XerT = presentValue(K, r, T);
			sigma_sqrtT = sigma * Math.sqrt(T);

			d1_numerator = Math.log(SedT / XerT) + (0.5 * sigma * sigma * T);
			d1 = d1_numerator / sigma_sqrtT;
			d2 = d1 - sigma_sqrtT;
			Nd1 = Gauss.PhiStandard(d1);
			Nd2 = Gauss.PhiStandard(d2);
			BScall = SedT * Nd1 - XerT * Nd2;
		} else {
			if (S > K) {
				BScall = S - K;
			} else {
				BScall = 0;
			}
		}
		return BScall;
	}

	public static double volatilidadImplicita(double cmarket, double S,
			double T, double K, double r, double d, double start) {

		double allowederror = 0.00001; // 'certeza de la solución
	    double boundfactor = 1.2; //  'debe de ser mayor que 1
	    double smallfraction = 0.000001; //  'casi cero
	    int maxiterations = 30; //max iterations
	    double maximplied = 10; // 'maximo para una solución relevante
	    double minimplied = 0.00001; //  'minimo
	    
	    double c, difference, highbound, isd = 0, lowbound, pmarket, SedT, XerT;
	    int iteration = 0;
	    	    
	    //'isd volatilidad implicita (desviación estandar)
	    //'Valores positivos son soluciones validas
	    //'Valores negativos no lo son!

	    //'darse por vencido si una condicion de ‘umbral no se cumple
	    SedT = S * Math.exp(-d * T);
	    XerT = K * Math.exp(-r * T);
	    
	    //'opcion call in-the-money
	    if (cmarket < (SedT - XerT)) isd = -1;
	    
	    if (cmarket > SedT) isd = -2;
	    
	    if (isd < 0) return isd;

	    //'darse por vencido si la opción es very deep in the ‘money ó very deep out
	    if (cmarket < (smallfraction * S)) isd = -3;
	    
	    pmarket = putfromcall(cmarket, S, T, K, r, d);
	    
	    if (pmarket < (smallfraction * S)) isd = -4;
	    
	    if (isd < 0) return isd;

	    //'encontrar los iniciales low and high bounds para la ‘solución
	    c = BScall(S, T, K, r, d, start);
	    
	    lowbound = start;
	    highbound = start;
	    
	    if (c < cmarket) {
	    	//System.out.println("entra aqui 1");
	        do {
	        	highbound = boundfactor * highbound;
	            c = BScall(S, T, K, r, d, highbound);
	            //System.out.println("cc " + highbound + " " + c + " " + maximplied);
	        } while (!(c > cmarket || highbound > maximplied)); //por el cambio del until en vb
	    } else { 
	    	//System.out.println("entra aqui 2");
	        do {
	            lowbound = lowbound / boundfactor;
	            c = BScall(S, T, K, r, d, lowbound);
	            //System.out.println("cc " + c + " " + cmarket + " " + highbound + " " + maximplied);
	        } while (!(c < cmarket || lowbound < minimplied)); // 'aqui se detiene el loop
	                                                           //por el cambio del until en vb
	    }
	    
	    //System.out.println("cc pp" + highbound + " " + c + " " + maximplied);
	    
	    //'darse por vencido si los iniciales bounds son extremos
	    if (lowbound < minimplied)  isd = -5;
	    if (highbound > maximplied) isd = -6;
	    if (isd < 0) return isd;
	    
	    //'El problema se puede solucionar subdividiendo el intervalo
	    isd = (lowbound + highbound) / 2;
	    
	    iteration = 0;
	    do {
	    	//System.out.println("entra aqui 3 " + isd);
	        iteration = iteration + 1;
	        c = BScall(S, T, K, r, d, isd);

	        if (c < cmarket) {
	            lowbound = isd;
	        } else {
	            highbound = isd;
	        }
	        
	        isd = (lowbound + highbound) / 2;
	        difference = highbound - lowbound;
	        
	        //System.out.println("dd " + c + " " + lowbound + " " + highbound + " " + difference + " " + allowederror + " " + iteration + " " + maxiterations);
	    } while (!(difference < allowederror || iteration >= maxiterations)); //por el cambio del until en vba
	    
	    
	    if (iteration >= maxiterations) isd = -7;
	    
	    return isd;
	}
	
	public static  void main(String[] args) { 
		//System.out.println("vola: " + VolatilidadImplicita.volatilidadImplicita(5.1, 50, 1, 55, .05, 0, .01));
		
		//System.out.println("vola: " + VolatilidadImplicita.BSput(50, .16666, 50, .1, 1.5, .1));
		//System.out.println("vol: " + VolatilidadImplicita.BScall(69, .5, 70, .05, 0, .35));
		
		//System.out.println("vol: " + VolatilidadImplicita.putfromcall(10, 20, .25, 30, .25, .2));
		
		//System.out.println();
	}
}
