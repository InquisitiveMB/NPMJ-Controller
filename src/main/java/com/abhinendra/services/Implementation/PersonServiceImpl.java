package com.abhinendra.services.Implementation;

import com.abhinendra.domain.Person;

import com.abhinendra.domain.QPerson;
import com.abhinendra.repositories.PersonRepository;
import com.abhinendra.services.PersonService;
import com.querydsl.core.types.Predicate;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("personService")
public class PersonServiceImpl implements PersonService {
    Predicate predicate;
    @Autowired
    PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person savePerson(Person person) throws Exception {
        return personRepository.save(person);
    }
	@Override
	public void readFile(String filename)
    {
        try 
        {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line = new String();
            Person person = new Person();
            while((line=br.readLine()) != null)
            {
                System.out.println(line);
                person = setParameters(line);
                try {
					savePerson(person);
				} catch (Exception e) {}
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
     }
    public boolean isAlphaNumeric(String s)
    {
        String pattern= "^[a-zA-Z0-9]*$";
        return s.matches(pattern);
    }
    
    @Override
    public Object findAll()
    {
    	return personRepository.findAll();
    }
    
    public Person setParameters(String line)
    {
        char CharArr[]=new char[127];
        CharArr=line.toCharArray();
        char TransChar[]=new char[12];
        char PayerName[]=new char[35];
        char PayeeName[]=new char[35];
        char PayerAccount[] = new char[12];
        char PayeeAccount[] = new char[12];
        String payeeName = null,payerName = null,str = "",RefId = null;
        float amount = 0;
        char Amount[] = new char[12];
        Date date = null;
        int i = 0;

    	for(int j = 0 ; j < 12 ; j++,i++)											// Extracting the reference ID of transaction
    		TransChar[j] = CharArr[i];
    	RefId=new String(TransChar);
    	
    	SimpleDateFormat formatter = new SimpleDateFormat("DD/MM/YYYY");
    	for(int j = 0 ; j < 8 ; j++,i++)
    	{
            str = str + CharArr[i];
            if( j == 1 || j == 3 )
            	str = str + '/';
    	}
        try {
            date=(Date) formatter.parse(str);										// Extracting the date of transaction
         }catch (ParseException e){
         }
        
        for(int j = 0 ; CharArr[i] != ' ' ; j++,i++)
            PayerName[j]=CharArr[i];
        i = 55;
        payerName = new String(PayerName);											// Extracting the Payer name of transaction
        
        for(int j = 0 ; j < 12 ; j++,i++)
        	PayerAccount[j] = CharArr[i];
        String payerAccount = new String(PayerAccount);								// Extracting the Payer Account of transaction
        
        for(int j = 0 ; CharArr[i] != ' ' ; j++,i++)
            PayeeName[j]=CharArr[i];
        i = 102;
        payeeName = new String(PayeeName);											// Extracting the Payee name of transaction
        
        for(int j = 0 ; j < 12 ; j++,i++)
        	PayeeAccount[j] = CharArr[i];
        String payeeAccount = new String(PayeeAccount);								// Extracting the Payer Account of transaction
        
        while(CharArr[i] == ' ' && i >= 114)
            i++;
        int index=i;           
        for(int j = 0 ; j < 127-index ; j++,i++)
            Amount[j] = CharArr[i];     
        String amt = new String(Amount);    
        amount = Float.parseFloat(amt);												// Extracting the Amount of transaction
        
        Person person = new Person(payerName,payeeName,date,amount);
        return person;
    }
}
