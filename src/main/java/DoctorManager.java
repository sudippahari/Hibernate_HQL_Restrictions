import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

public class DoctorManager {

	public static void main(String[] args) {

		Configuration cfg = new Configuration();
		cfg.configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session s = sf.openSession();
		Transaction tx = s.beginTransaction();

		/*
		 * Doctor d1 = new Doctor("Namuna Adhikari", "Physician"); Doctor d2 =
		 * new Doctor("Suraj Poudel", "Neuro Sergeon"); Doctor d3 = new
		 * Doctor("Lomas Paudel", "Cirapractor"); Doctor d4 = new
		 * Doctor("Dinanath Subedi", "Dentist");
		 * 
		 * s.save(d1); s.save(d2); s.save(d3); s.save(d4);
		 */

		// HQL query for bulk data
		Query query = s.createQuery("from Doctor");
		List<Doctor> doctors = query.list();
		System.out.println("==================================================================");
		for (Doctor d : doctors) {
			System.out.println(d.getId() + "\t" + d.getName() + "\t" + d.getTitle());
		}
		s.flush();

		// criteria for restriction
		Criteria cr = s.createCriteria(Doctor.class).add(Restrictions.like("name", "N%"));
		List<Doctor> doctors1 = cr.list();
		System.out.println("===================================================================");
		for (Doctor d : doctors1) {
			System.out.println(d.getId() + "\t" + d.getName() + "\t" + d.getTitle());
		}

		// criteria for restriction
		Criteria cr1 = s.createCriteria(Doctor.class).add(Restrictions.between("id", 1, 3));
		List<Doctor> doctors2 = cr1.list();
		System.out.println("===================================================================");
		for (Doctor d : doctors2) {
			System.out.println(d.getId() + "\t" + d.getName() + "\t" + d.getTitle());
		}

		tx.commit();
		s.close();

	}

}
