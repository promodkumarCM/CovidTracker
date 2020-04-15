package nl.psdcompany.duonavigationdrawer.example.country;


public class CountryApiModel2 {


private Integer ourid;
private String title;
private String code;
private String source;
private Integer totalCases;
private Integer totalRecovered;
private Integer totalUnresolved;
private Integer totalDeaths;
private Integer totalNewCasesToday;
private Integer totalNewDeathsToday;
private Integer totalActiveCases;
private Integer totalSeriousCases;


    public CountryApiModel2(Integer totalCases, Integer totalRecovered, Integer totalUnresolved, Integer totalDeaths, Integer totalNewCasesToday, Integer totalNewDeathsToday, Integer totalActiveCases, Integer totalSeriousCases) {
        super();
        this.title=title;
        this.code=code;
        this.totalCases = totalCases;
        this.totalRecovered = totalRecovered;
        this.totalUnresolved = totalUnresolved;
        this.totalDeaths = totalDeaths;
        this.totalNewCasesToday = totalNewCasesToday;
        this.totalNewDeathsToday = totalNewDeathsToday;
        this.totalActiveCases = totalActiveCases;
        this.totalSeriousCases = totalSeriousCases;
    }

public Integer getOurid() {
return ourid;
}

public void setOurid(Integer ourid) {
this.ourid = ourid;
}

public String getTitle() {
return title;
}

public void setTitle(String title) {
this.title = title;
}

public String getCode() {
return code;
}

public void setCode(String code) {
this.code = code;
}

public String getSource() {
return source;
}

public void setSource(String source) {
this.source = source;
}

public Integer getTotalCases() {
return totalCases;
}

public void setTotalCases(Integer totalCases) {
this.totalCases = totalCases;
}

public Integer getTotalRecovered() {
return totalRecovered;
}

public void setTotalRecovered(Integer totalRecovered) {
this.totalRecovered = totalRecovered;
}

public Integer getTotalUnresolved() {
return totalUnresolved;
}

public void setTotalUnresolved(Integer totalUnresolved) {
this.totalUnresolved = totalUnresolved;
}

public Integer getTotalDeaths() {
return totalDeaths;
}

public void setTotalDeaths(Integer totalDeaths) {
this.totalDeaths = totalDeaths;
}

public Integer getTotalNewCasesToday() {
return totalNewCasesToday;
}

public void setTotalNewCasesToday(Integer totalNewCasesToday) {
this.totalNewCasesToday = totalNewCasesToday;
}

public Integer getTotalNewDeathsToday() {
return totalNewDeathsToday;
}

public void setTotalNewDeathsToday(Integer totalNewDeathsToday) {
this.totalNewDeathsToday = totalNewDeathsToday;
}

public Integer getTotalActiveCases() {
return totalActiveCases;
}

public void setTotalActiveCases(Integer totalActiveCases) {
this.totalActiveCases = totalActiveCases;
}

public Integer getTotalSeriousCases() {
return totalSeriousCases;
}

public void setTotalSeriousCases(Integer totalSeriousCases) {
this.totalSeriousCases = totalSeriousCases;
}

}