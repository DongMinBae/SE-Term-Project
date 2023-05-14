/*
 * Restaurant Booking System: example code to accompany
 *
 * "Practical Object-oriented Design with UML"
 * Mark Priestley
 * McGraw-Hill (2004)
 */

package booksys.application.persistency ;

import booksys.storage.* ;

import java.sql.* ;
import java.util.Enumeration ;
import java.util.Hashtable ;


//고객 객체의 정보 표현을 나타내는 클래스
public class CustomerMapper
{
  // Implementation of hidden cache
  
  private Hashtable cache ;

  private PersistentCustomer getFromCache(int oid)
  {
    Integer key = new Integer(oid) ;
    return (PersistentCustomer) cache.get(key) ;
  }

  private PersistentCustomer getFromCacheByDetails(String name, String phone)
  {
    PersistentCustomer c = null ;
    Enumeration enum = cache.elements() ;
    while (c == null && enum.hasMoreElements()) {
      PersistentCustomer tmp = (PersistentCustomer) enum.nextElement() ;
      if (name.equals(tmp.getName()) && phone.equals(tmp.getPhoneNumber())) {
	c = tmp ;
      }
    }
    return c ;
  }

  private void addToCache(PersistentCustomer c)
  {
    Integer key = new Integer(c.getId()) ;
    cache.put(key, c) ;
  }
  
  // Constructor:
  
  private CustomerMapper()
  {
    cache = new Hashtable() ;
  }

  // Singleton:
  
  private static CustomerMapper uniqueInstance ;

  public static CustomerMapper getInstance()
  {
    if (uniqueInstance == null) {
      uniqueInstance = new CustomerMapper() ;
    }
    return uniqueInstance ;
  }


  //Customer 객체를 검색하기 위한 기본 메소드.
  //name과 phone으로 고객 객체를 확인하고 없으면 이름과 전화번호로 SELECT문으로 데이터베이스에서 고객 객체를 확인한다.
  // 없다면 새 고객 객체를 만들어 추가한다.
  public PersistentCustomer getCustomer(String n, String p)
  {
    PersistentCustomer c = getFromCacheByDetails(n, p) ;
    if (c == null) {
      c = getCustomer("SELECT * FROM Customer WHERE name = '" + n
		      + "' AND phoneNumber = '" + p + "'") ;
      if (c == null) {
	c = createCustomer(n, p) ;
      }
      addToCache(c) ;
    }
    return c ;
  }
  

  //고유한 객체 식별자로 고객 객체를 검색한다.
  PersistentCustomer getCustomerForOid(int oid)
  {
    PersistentCustomer c = getFromCache(oid) ;
    if (c == null) {
      c = getCustomer("SELECT * FROM Customer WHERE oid ='" + oid + "'") ;
      if (c != null) {
	addToCache(c) ;
      }
    }
    return c ;
  }

  // Add a customer to the database
  

  //데이터베이스에 새 고객 객체를 추가한다. 동일한 객체가 있는지 우선 확인하고 없다면 
  //INSERT문을 실행하여 새 고객을 데이터베이스에 추가하고 새로 생성된 고객을 데이터베이스에서 검색한다.
  PersistentCustomer createCustomer(String name, String phone)
  {
    PersistentCustomer c = getFromCacheByDetails(name, phone) ;
    if (c == null) {
      try {
	Statement stmt
	  = Database.getInstance().getConnection().createStatement() ;
	int updateCount
	  = stmt.executeUpdate("INSERT INTO Customer (name, phoneNumber)" +
			       "VALUES ('" + name + "', '" + phone + "')") ;
	stmt.close() ;
      }
      catch (SQLException e) {
	e.printStackTrace() ;
      }
      c = getCustomer(name, phone) ;
    }
    return c ;
  }


  //
  private PersistentCustomer getCustomer(String sql)
  {
    PersistentCustomer c = null ;
    try {
      Statement stmt
	= Database.getInstance().getConnection().createStatement() ;
      ResultSet rset
	= stmt.executeQuery(sql) ;
      while (rset.next()) {
	int    oid   = rset.getInt(1) ;
	String name  = rset.getString(2) ;
	String phone = rset.getString(3) ;
	c = new PersistentCustomer(oid, name, phone) ;
      }
      rset.close() ;
      stmt.close() ;
    }
    catch (SQLException e) {
      e.printStackTrace() ;
    }
    return c ;
  }
}
