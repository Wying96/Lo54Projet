
import fr.utbm.entity.Location;
import fr.utbm.repository.LocationDaoImp;
import fr.utbm.tools.HibernateUtil;
import java.util.List;
import org.hibernate.Session;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author wuying
 */
public class NewClass {
    public static void main(String[] args) {
        //Session session = HibernateUtil.getSessionFactory().openSession();
        
LocationDaoImp lp=new LocationDaoImp();
    List<Location> ls = lp.findAll();
    
    for(Location l: ls){
    System.out.println(l.toString());
    
}
System.exit(0);
    }
}
