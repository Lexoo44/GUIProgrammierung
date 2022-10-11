package net.tfobz.ratenrechner;

public class RatenRechner{

	private boolean nachschuessig = true;
	private double barwert = -1;
	private double jahreszinssatz = -1;
	private double laufzeitInJahren = -1;
	private int ratenProJahr = -1;
	private double rate = -1;
	
	
	
	public boolean getNachschuessig() {
		return nachschuessig;
	}

	public void setNachschuessig(boolean nachschuessig) {
		this.nachschuessig = nachschuessig;
	}
	
	
	public String getRatenProJahr() {
		return ratenProJahr + "";
	}
	
	public void setRatenProJahr(String rpj) throws RatenRechnerException{
		int ratenProJahr = Integer.parseInt(rpj);
		if(ratenProJahr == 1 || ratenProJahr == 4|| ratenProJahr == 6|| ratenProJahr == 12) {
			this.ratenProJahr = Integer.parseInt(rpj);
		}
		else {
			throw new RatenRechnerException("Falsche eingabe der Raten pro Jahr");
		}
	}
	
	
	
	public void setJahresZinssatz(String jzs) throws RatenRechnerException{
		double JahresZinssatz = Double.parseDouble(jzs);
		try {
			if(JahresZinssatz < 0 || barwert == 0) {
				throw new RatenRechnerException("Kein gültiger Gleitkommawert");
			}else{
				this.jahreszinssatz = JahresZinssatz;
			}
		}catch(NumberFormatException e) {
			throw new RatenRechnerException("Kein gültiger Gleitkommawert");
		}
	}
	
	public String getJahresZinssatz() {
		return jahreszinssatz +"";
	}
	
	

    public String getBarwert() throws RatenRechnerException {
    	String ret = "";
        if (jahreszinssatz != -1 && laufzeitInJahren != -1 && ratenProJahr != -1 && rate != -1) {
            final double n = laufzeitInJahren * ratenProJahr;
            final double q = 1. + (jahreszinssatz / ratenProJahr) / 100.;
            if (nachschuessig)
                barwert = Math.round((rate * (Math.pow(q, n) - 1.) / (Math.pow(q, n) * (q - 1.))));
            else
                barwert = Math.round((rate * (Math.pow(q, n)- 1.) / (Math.pow(q, n - 1.) * (q - 1.))));
        } else {
            throw new RatenRechnerException("Jahreszinssatz, Laufzeit, Raten pro Jahr oder Rate nicht gesetzt");
        }
        ret = ret + barwert;
        return ret;
    }
    
    public void setBarwert(String b) throws RatenRechnerException{
    	double barwert = Double.parseDouble(b);
		try {
			if(barwert < 0 || barwert == 0) {
				throw new RatenRechnerException("Kein gültiger Gleitkommawert");
			}
		this.barwert = barwert;
		}catch(NumberFormatException e) {
			throw new RatenRechnerException("Kein gültiger Gleitkommawert");
		}
	}

    public String getLaufzeitInJahren() throws RatenRechnerException {
    	String ret = "";
        if (jahreszinssatz != -1 && barwert != -1 && ratenProJahr != -1 && rate != -1) {
            final double q = 1. + (jahreszinssatz / ratenProJahr) / 100.;

            if (nachschuessig)
                laufzeitInJahren = Math.round(((-Math.log((rate - barwert * (q - 1.)) / rate)
                        / Math.log(q)) / ratenProJahr) * 100) / 100;
            else
                laufzeitInJahren = Math.round(((1. - Math.log((q * rate - barwert * (q - 1.)) / rate)
                        / Math.log(q)) / ratenProJahr));
        } else {
            throw new RatenRechnerException("Jahreszinssatz, Barwert, Raten pro Jahr oder Rate nicht gesetzt");
        }
        ret = ret + laufzeitInJahren;
        return ret;
    }
    
    public void setLaufZeitInJahren(String lij) throws RatenRechnerException{
    	double laufzeitInJahren = Double.parseDouble(lij);
		try {
			if(laufzeitInJahren < 0 || laufzeitInJahren == 0) {
				throw new RatenRechnerException("Kein gültiger Gleitkommawert");
			}else{
				this.laufzeitInJahren = laufzeitInJahren;
			}
		}catch(NumberFormatException e) {
			throw new RatenRechnerException("Kein gültiger Gleitkommawert");
		}
	}

    
    public String getRate() throws RatenRechnerException {
    	String ret = "";

        if (jahreszinssatz != -1 && barwert != -1 && ratenProJahr != -1 && laufzeitInJahren != -1) {
            final double n = laufzeitInJahren * ratenProJahr;
            final double q = 1. + (jahreszinssatz / ratenProJahr) / 100.;
            if (nachschuessig)
                rate = Math.round(barwert * (Math.pow(q, n) * (q - 1.)) / (Math.pow(q, n) - 1.));
            else
                rate = Math.round(barwert * (Math.pow(q, n - 1.) * (q - 1.)) / (Math.pow(q, n) - 1.));
        } else {
            throw new RatenRechnerException("Jahreszinssatz, Barwert, Raten pro Jahr oder Laufzeit nicht gesetzt");
        }
        ret = ret + rate;
        return ret;
    }
    
    public void setRate(String r) throws RatenRechnerException{
    	double rate = Double.parseDouble(r);
		try {
			if(rate < 0 || rate == 0) {
				throw new RatenRechnerException("Kein gültiger Gleitkommawert");
			}else{
				this.rate = rate;
			}
		}catch(NumberFormatException   e) {
			throw new RatenRechnerException("Kein gültiger Gleitkommawert");
		}
	}
    
    
    public String getTilgungsplan() throws RatenRechnerException {
    	String ret = "";
			ret += "<!DOCTYPE html>";
			ret += "<html>";
			
			ret += "<h1>T I L G U N G S P L A N</h1>";
			ret += "<table border=\"2\">";
			if (nachschuessig) {
				ret += "<tr><td>Zahlungsart:</td><td>Nachschüssig</td></tr>";
			} else {
				ret += "<tr><td>Zahlungsart:</td><td>Vorschüssig</td></tr>";
			}
			ret += "<tr><td>Barwert:</td><td>" + barwert + "</td></tr>";
			ret += "<tr><td>Jahreszinssatz:</td><td>" + jahreszinssatz + "</td></tr>";
			ret += "<tr><td>Laufzeit in Jahren:</td><td>" + laufzeitInJahren + "</td></tr>";
			ret += "<tr><td>Rückzahlungsart:</td><td>" + ratenProJahr + " Raten im Jahr</td></tr>";
			ret += "<tr><td>Rate:</td><td>" + rate + "</td></tr>";
			ret += "</table>";
						
			ret += "<table border=\"2\" width=\"400\">";
			ret += "<tr><th>Periode</th><th>Rate</th><th>Restkapital</th><th>Zinsen</th></tr>";
			
			final double q = 1. + (jahreszinssatz / ratenProJahr) / 100.;
			double restkapital = barwert;
			for (int i = 1; i <= (int)(laufzeitInJahren*ratenProJahr); ++i) {
				ret += "<tr><td>" + i + "</td><td>" + rate + "</td>";
				final double zinsen;
				if (nachschuessig) {
					zinsen = (double) Math.round((restkapital * (q - 1.)));
					restkapital = (double) Math.round((restkapital * q - rate));
					ret += "<td>" + restkapital + "</td><td>" + zinsen + "</td>";
				} 
				else {
					zinsen = (double) Math.round((restkapital * (q - 1.)));
					restkapital = (double) Math.round((restkapital * q - rate));
					ret += "<td>" + restkapital + "</td><td>" + zinsen + "</td>";
				}
				ret += "</tr>";
			}
			ret += "</table>";
			ret += "</html>";
		return ret;
	}
}
