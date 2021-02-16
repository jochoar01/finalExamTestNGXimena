package Data;

import academyapi.Users;

import java.util.ArrayList;

public class DataGenerator {
    private String[] id;
    private String[] firstName={"FrenchVirus","RiderDr","BronzeSunrise","DearAim","ShootNeer","MajStrong","SolSword","CovidJackpot","HealMorphine","FreedomSynn","WaitFire","PokeZero","BlackCovid","AlphaSnipe","PrincessSmall","SushiOnix","DiabèteNever","UraniumHack","SniperSniper","RiverRiku"};
    private String[] lastName={"FrenchVirus","RiderDr","BronzeSunrise","DearAim","ShootNeer","MajStrong","SolSword","CovidJackpot","HealMorphine","FreedomSynn","WaitFire","PokeZero","BlackCovid","AlphaSnipe","PrincessSmall","SushiOnix","DiabèteNever","UraniumHack","SniperSniper","RiverRiku"};
    private String[] email;
    private String[] country={"Albania","Alemania","Andorra","Angola","Antigua","ArabiaSaudita","Argelia","Argentina","Armenia","Australia","Austria","Azerbaiyán","Bahamas","Bahrein","Bangladesh","Barbados","Belarús","Belice","Benin","Bhután"};
    private String[] telephone={"61(344)921-85-70891","4(8994)827-25-18762","1(1490)618-63-00190","3(950)120-99-22113","04(753)845-94-50789","91(224)562-60-77525","6(8158)082-98-68010","62(3454)395-90-19834","846(34)259-39-66800","43(4864)533-00-24638","1(61)581-38-27255","421(849)413-15-12739","857(9527)725-24-77216","6(4736)199-07-47733","017(79)460-53-12160","20(106)446-87-69932","20(91)339-49-99106","0(257)455-44-87118","1(8427)278-37-56889","68(1326)073-89-47880"};
    private Boolean[] active={true,false};
    private String[] jobTitle={"Aerospace Engineering and Operations Technicians","Aerospace Engineers","Agricultural Engineers","Architects, Except Landscape and Naval","Architectural and Civil Drafters","Architectural Drafters","Automotive Engineering Technicians","Automotive Engineers","Biochemical Engineers","Biomedical Engineers","Cartographers and Photogrammetrists","Chemical Engineers","Civil Drafters","Civil Engineering Technicians","Civil Engineers","Computer Hardware Engineers","Drafters, All Other","Electrical and Electronic Engineering Technicians","Electrical and Electronics Drafters","Electrical Drafters"};



    public int randomGenerator(){
        return  (int)(Math.random()*20);
    }

    public ArrayList<Users> usersGenerator (int dataAmount){
        ArrayList<Users> data = new ArrayList<>();
        for (int i = 0; i < dataAmount ; i++) {
            String firstName=this.firstName[randomGenerator()];
            String lastName=this.lastName[randomGenerator()];
            String email=firstName+"."+lastName+"@gmail.com";
            String country=this.country[randomGenerator()];
            String telephone=this.telephone[randomGenerator()];
            Boolean active=this.active[randomGenerator()%2];
            String jobTitle=this.jobTitle[randomGenerator()];
            data.add(new Users(firstName,lastName,email,country,telephone,active,jobTitle));
        }
      return data;
    }
}
