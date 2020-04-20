package com.example.a3350.application;

import java.sql.ResultSet;
import java.sql.Statement;

public class Database
{
    private Statement statement;
    Database(Statement statement)
    {
        this.statement = statement;
        createTables();
        insertInstitutions();
        insertUsers();
        insertCourses();
        insertPostings();
        insertRatings();
        closeConnection();
    }

    private void createTables()
    {
        try
        {
            ResultSet resultSet1 = statement.executeQuery("CREATE TABLE INSTITUTION(NAME VARCHAR(60) NOT NULL,DOMAIN VARCHAR(60) NOT NULL PRIMARY KEY,UNIQUE(NAME));");
            ResultSet resultSet2 = statement.executeQuery("CREATE TABLE USER(NAME VARCHAR(60) NOT NULL,EMAIL VARCHAR(60) NOT NULL PRIMARY KEY,PASSWORD VARCHAR(40) NOT NULL,INSTITUTION VARCHAR(60) NOT NULL,UNIQUE(NAME),FOREIGN KEY(INSTITUTION) REFERENCES PUBLIC.INSTITUTION(NAME));");
            ResultSet resultSet3 = statement.executeQuery("CREATE TABLE COURSE(FACULTY VARCHAR(60) NOT NULL,CID INTEGER NOT NULL,NAME VARCHAR(100) NOT NULL,INSTITUTION VARCHAR(60) NOT NULL,PRIMARY KEY(FACULTY,CID),FOREIGN KEY(INSTITUTION) REFERENCES PUBLIC.INSTITUTION(NAME));");
            ResultSet resultSet4 = statement.executeQuery("CREATE TABLE POSTING(PID INTEGER NOT NULL PRIMARY KEY, FACULTY VARCHAR(60) NOT NULL,CID INTEGER NOT NULL,TITLE VARCHAR(100) NOT NULL,PRICE DOUBLE NOT NULL,DETAIL VARCHAR(16777216) NOT NULL,HIGHLIGHTED BOOLEAN NOT NULL,HOWOLD INTEGER NOT NULL,OWNER VARCHAR(50) NOT NULL,FOREIGN KEY(FACULTY,CID) REFERENCES PUBLIC.COURSE(FACULTY,CID),FOREIGN KEY(OWNER) REFERENCES PUBLIC.USER(EMAIL) ON DELETE CASCADE)");
            ResultSet resultSet5 = statement.executeQuery("CREATE TABLE RATING(RATED VARCHAR(60) NOT NULL,RATING DOUBLE NOT NULL,RATER VARCHAR(60) NOT NULL, FOREIGN KEY (RATED) REFERENCES USER(EMAIL) ON DELETE CASCADE, FOREIGN KEY (RATER) REFERENCES USER(EMAIL));");
            resultSet1.close();
            resultSet2.close();
            resultSet3.close();
            resultSet4.close();
            resultSet5.close();
        }
        catch (Exception e)
        {
            System.out.println("Error in creating tables.");
            e.printStackTrace();
        }
    }

    private void insertInstitutions()
    {
        try
        {
            ResultSet resultSet1 = statement.executeQuery("INSERT INTO INSTITUTION VALUES('Khan Academy','@khandemy.us');");
            ResultSet resultSet2 = statement.executeQuery("INSERT INTO INSTITUTION VALUES('Red River College','@myrrc.ca');");
            ResultSet resultSet3 = statement.executeQuery("INSERT INTO INSTITUTION VALUES('University of Manitoba','@myumanitoba.ca');");
            ResultSet resultSet4 = statement.executeQuery("INSERT INTO INSTITUTION VALUES('University of Winnipeg','@myuwinnipeg.ca');");
            ResultSet resultSet5 = statement.executeQuery("INSERT INTO INSTITUTION VALUES('Oxford University','@oxoxox.uk');");
            ResultSet resultSet6 = statement.executeQuery("INSERT INTO INSTITUTION VALUES('Hogwarts School of Witchcraft and Wizardry','@hogwiz.uk');");
            resultSet1.close();
            resultSet2.close();
            resultSet3.close();
            resultSet4.close();
            resultSet5.close();
            resultSet6.close();
        }
        catch (Exception e)
        {
            System.out.println("Error in inserting institution values.");
            e.printStackTrace();
        }
    }

    private void insertUsers()
    {
        try
        {
            ResultSet resultSet1 = statement.executeQuery("INSERT INTO USER VALUES('Michael Bluth','mbluth@myumanitoba.ca','123456','University of Manitoba');");
            ResultSet resultSet2 = statement.executeQuery("INSERT INTO USER VALUES('Quoc Vi Nguyen','nguyenvq@myumanitoba.ca','123456','University of Manitoba');");
            ResultSet resultSet3 = statement.executeQuery("INSERT INTO USER VALUES('Luis Nguyen','thehaonguyen@myumanitoba.ca','21012003','University of Manitoba');");
            ResultSet resultSet4 = statement.executeQuery("INSERT INTO USER VALUES('Tin Trong Truong','tintt@myumanitoba.ca','thichbagia','University of Manitoba');");
            ResultSet resultSet5 = statement.executeQuery("INSERT INTO USER VALUES('Thao Vy Tran Nguyen','tranvt@myumanitoba.ca','299Hudson','University of Manitoba');");

            ResultSet resultSet6 = statement.executeQuery("INSERT INTO USER VALUES('Lucille Bluth','lucilleb@hogwiz.uk','aMatterOfLand','Hogwarts School of Witchcraft and Wizardry');");
            ResultSet resultSet7 = statement.executeQuery("INSERT INTO USER VALUES('Harry Potter','pharry@hogwiz.uk','HappyBirthday1','Hogwarts School of Witchcraft and Wizardry');");
            ResultSet resultSet8 = statement.executeQuery("INSERT INTO USER VALUES('Ron Weasley','ronweasley@hogwiz.uk','Hogwarts123','Hogwarts School of Witchcraft and Wizardry');");

            ResultSet resultSet9 = statement.executeQuery("INSERT INTO USER VALUES('John Dorian','jaydee@myrcc.ca','DrCox123','Red River College');");
            ResultSet resultSet10 = statement.executeQuery("INSERT INTO USER VALUES('Chris Turk','christ@myrcc.ca','Warrior100','Red River College');");
            ResultSet resultSet11 = statement.executeQuery("INSERT INTO USER VALUES('Perry Cox','coxperry@myrcc.ca','OhDearGod100','Red River College');");
            ResultSet resultSet12 = statement.executeQuery("INSERT INTO USER VALUES('Robert Kelso','kelsorob@myrcc.ca','TurkTurkelton','Red River College');");

            ResultSet resultSet13 = statement.executeQuery("INSERT INTO USER VALUES('Sherlock Holmes','sherlock@oxoxox.uk','ElementaryMyDearWatson','Oxford University');");
            ResultSet resultSet14 = statement.executeQuery("INSERT INTO USER VALUES('John Watson','jwatson@oxoxox.uk','scienceOfDeduction1','Oxford University');");
            ResultSet resultSet15 = statement.executeQuery("INSERT INTO USER VALUES('Irene Adler','adlerire@oxoxox.uk','Sherlocked221','Oxford University');");
            resultSet1.close();
            resultSet2.close();
            resultSet3.close();
            resultSet4.close();
            resultSet5.close();
            resultSet6.close();
            resultSet7.close();
            resultSet8.close();
            resultSet9.close();
            resultSet10.close();
            resultSet11.close();
            resultSet12.close();
            resultSet13.close();
            resultSet14.close();
            resultSet15.close();
        }
        catch (Exception e)
        {
            System.out.println("Error in inserting user values.");
            e.printStackTrace();
        }
    }

    private void insertCourses()
    {
        try
        {
            ResultSet resultSet1 = statement.executeQuery("INSERT INTO COURSE VALUES('ABIZ',1000,'Introduction to Agribusiness Management','University of Manitoba');");
            ResultSet resultSet2 = statement.executeQuery("INSERT INTO COURSE VALUES('BIO',1000,'Foundations Of Life','University of Manitoba');");
            ResultSet resultSet3 = statement.executeQuery("INSERT INTO COURSE VALUES('COMP',3030,'Automata','University of Manitoba');");
            ResultSet resultSet4 = statement.executeQuery("INSERT INTO COURSE VALUES('COMP',3170,'Analysis of Algorithms and Data Structures','University of Manitoba');");
            ResultSet resultSet5 = statement.executeQuery("INSERT INTO COURSE VALUES('COMP',3380,'Database Concepts','University of Manitoba');");
            ResultSet resultSet6 = statement.executeQuery("INSERT INTO COURSE VALUES('COMP',3490,'Computer Graphics','University of Manitoba');");
            ResultSet resultSet7 = statement.executeQuery("INSERT INTO COURSE VALUES('ECONOMIC',1020,'Introduction To Macroeconomic Principles','University of Manitoba');");
            ResultSet resultSet8 = statement.executeQuery("INSERT INTO COURSE VALUES('ECONOMIC',1010,'Introduction To Micro economic Principles','University of Manitoba');");
            ResultSet resultSet9 = statement.executeQuery("INSERT INTO COURSE VALUES('ENG',3130,'Engineering','University of Manitoba');");

            ResultSet resultSet10 = statement.executeQuery("INSERT INTO COURSE VALUES('BIZ',1120,'Applied Accounting I','Red River College');");
            ResultSet resultSet11 = statement.executeQuery("INSERT INTO COURSE VALUES('BIZ',2120,'Applied Accounting II','Red River College');");
            ResultSet resultSet12 = statement.executeQuery("INSERT INTO COURSE VALUES('BIZ',2740,'Legal Assistant','Red River College');");
            ResultSet resultSet13 = statement.executeQuery("INSERT INTO COURSE VALUES('HOSP',1020,'Hospitality and Tourism Management','Red River College');");
            ResultSet resultSet14 = statement.executeQuery("INSERT INTO COURSE VALUES('HOSP',2120,'Culinary Arts','Red River College');");
            ResultSet resultSet15 = statement.executeQuery("INSERT INTO COURSE VALUES('COMS',1450,'ASL and Deaf Studies','Red River College');");
            ResultSet resultSet16 = statement.executeQuery("INSERT INTO COURSE VALUES('COMS',1780,'Child and Youth Care','Red River College');");

            ResultSet resultSet17 = statement.executeQuery("INSERT INTO COURSE VALUES('PHY',1000,'Physics I','Khan Academy');");
            ResultSet resultSet18 = statement.executeQuery("INSERT INTO COURSE VALUES('PHY',2000,'Physics II','Khan Academy');");
            ResultSet resultSet19 = statement.executeQuery("INSERT INTO COURSE VALUES('PHY',3000,'Physics III','Khan Academy');");
            ResultSet resultSet20 = statement.executeQuery("INSERT INTO COURSE VALUES('CHEM',1000,'Chemistry I','Khan Academy');");
            ResultSet resultSet21 = statement.executeQuery("INSERT INTO COURSE VALUES('CHEM',2000,'Chemistry II','Khan Academy');");
            ResultSet resultSet22 = statement.executeQuery("INSERT INTO COURSE VALUES('CHEM',3000,'Chemistry III','Khan Academy');");
            ResultSet resultSet23 = statement.executeQuery("INSERT INTO COURSE VALUES('BIO',1500,'Biology I','Khan Academy');");
            ResultSet resultSet24 = statement.executeQuery("INSERT INTO COURSE VALUES('BIO',2000,'Biology II','Khan Academy');");

            ResultSet resultSet25 = statement.executeQuery("INSERT INTO COURSE VALUES('HIS',20,'History of Magic','Hogwarts School of Witchcraft and Wizardry');");
            ResultSet resultSet26 = statement.executeQuery("INSERT INTO COURSE VALUES('HIS',25,'Study of Ancient Runes','Hogwarts School of Witchcraft and Wizardry');");
            ResultSet resultSet27 = statement.executeQuery("INSERT INTO COURSE VALUES('PLA',15,'Herbology','Hogwarts School of Witchcraft and Wizardry');");
            ResultSet resultSet28 = statement.executeQuery("INSERT INTO COURSE VALUES('MAG',10,'Flying','Hogwarts School of Witchcraft and Wizardry');");
            ResultSet resultSet29 = statement.executeQuery("INSERT INTO COURSE VALUES('MAG',15,'Potions','Hogwarts School of Witchcraft and Wizardry');");
            ResultSet resultSet30 = statement.executeQuery("INSERT INTO COURSE VALUES('MAG',25,'Transfiguration','Hogwarts School of Witchcraft and Wizardry');");
            ResultSet resultSet31 = statement.executeQuery("INSERT INTO COURSE VALUES('FUT',35,'Divination','Hogwarts School of Witchcraft and Wizardry');");

            ResultSet resultSet32 = statement.executeQuery("INSERT INTO COURSE VALUES('ANTH',1100,'Anthropology I','University of Winnipeg');");
            ResultSet resultSet33 = statement.executeQuery("INSERT INTO COURSE VALUES('ANTH',2100,'Anthropology II','University of Winnipeg');");
            ResultSet resultSet34 = statement.executeQuery("INSERT INTO COURSE VALUES('LANG',4600,'German','University of Winnipeg');");
            ResultSet resultSet35 = statement.executeQuery("INSERT INTO COURSE VALUES('LANG',1500,'Spanish','University of Winnipeg');");
            ResultSet resultSet36 = statement.executeQuery("INSERT INTO COURSE VALUES('LANG',2500,'French','University of Winnipeg');");
            ResultSet resultSet37 = statement.executeQuery("INSERT INTO COURSE VALUES('PSY',2000,'Introduction to Psychology I','University of Winnipeg');");
            ResultSet resultSet38 = statement.executeQuery("INSERT INTO COURSE VALUES('PSY',2200,'Introduction to Psychology II','University of Winnipeg');");

            ResultSet resultSet39 = statement.executeQuery("INSERT INTO COURSE VALUES('ANTH',1000,'Archaeology and Anthropology','Oxford University');");
            ResultSet resultSet40 = statement.executeQuery("INSERT INTO COURSE VALUES('ANTH',2000,'Archaeology and Anthropology II','Oxford University');");
            ResultSet resultSet41 = statement.executeQuery("INSERT INTO COURSE VALUES('ERTH',1500,'Earth Science','Oxford University');");
            ResultSet resultSet42 = statement.executeQuery("INSERT INTO COURSE VALUES('ERTH',2500,'Geology','Oxford University');");
            ResultSet resultSet43 = statement.executeQuery("INSERT INTO COURSE VALUES('LAW',1100,'Criminal law','Oxford University');");
            ResultSet resultSet44 = statement.executeQuery("INSERT INTO COURSE VALUES('LAW',1600,'A Roman introduction to private law','Oxford University');");
            ResultSet resultSet45 = statement.executeQuery("INSERT INTO COURSE VALUES('LAW',2700,'European Union law','Oxford University');");

            resultSet1.close();
            resultSet2.close();
            resultSet3.close();
            resultSet4.close();
            resultSet5.close();
            resultSet6.close();
            resultSet7.close();
            resultSet8.close();
            resultSet9.close();
            resultSet10.close();
            resultSet11.close();
            resultSet12.close();
            resultSet13.close();
            resultSet14.close();
            resultSet15.close();
            resultSet16.close();
            resultSet17.close();
            resultSet18.close();
            resultSet19.close();
            resultSet20.close();
            resultSet21.close();
            resultSet22.close();
            resultSet23.close();
            resultSet24.close();
            resultSet25.close();
            resultSet26.close();
            resultSet27.close();
            resultSet28.close();
            resultSet29.close();
            resultSet30.close();
            resultSet31.close();
            resultSet32.close();
            resultSet33.close();
            resultSet34.close();
            resultSet35.close();
            resultSet36.close();
            resultSet37.close();
            resultSet38.close();
            resultSet39.close();
            resultSet40.close();
            resultSet41.close();
            resultSet42.close();
            resultSet43.close();
            resultSet44.close();
            resultSet45.close();
        }
        catch (Exception e)
        {
            System.out.println("Error in inserting courses values.");
            e.printStackTrace();
        }
    }

    private void insertPostings()
    {
        try
        {
            ResultSet resultSet1 = statement.executeQuery("INSERT INTO POSTING VALUES(0,'COMP',3030,'Automata Book for COMP3030',59.99E0,'This book is required for COMP3030 and I suggest you get it asap',true,10,'nguyenvq@myumanitoba.ca');");
            ResultSet resultSet2 = statement.executeQuery("INSERT INTO POSTING VALUES(1,'COMP',3030,'Introduction to Automata Theory, Languages, and Computation',120,'Introduction to Automata Theory, Languages, and Computation is an influential computer science textbook by John Hopcroft and Jeffrey Ullman on formal languages and the theory of computation.',true,48,'mbluth@myumanitoba.ca');");
            ResultSet resultSet3 = statement.executeQuery("INSERT INTO POSTING VALUES(2,'ABIZ',1000,'Principles of agribusiness management',40,'Great condition, not touched',false,1,'tranvt@myumanitoba.ca');");
            ResultSet resultSet4 = statement.executeQuery("INSERT INTO POSTING VALUES(3,'BIO',1000,'Biology Principles: A Critical Study',200,'Please buy it. Need money',true,20,'thehaonguyen@myumanitoba.ca');");
            ResultSet resultSet5 = statement.executeQuery("INSERT INTO POSTING VALUES(4,'COMP',3170,'The Design and Analysis of Computer Algorithms',125,'With this text, you gain an understanding of the fundamental concepts of algorithms, the very heart of computer science',true,6,'mbluth@myumanitoba.ca');");
            ResultSet resultSet6 = statement.executeQuery("INSERT INTO POSTING VALUES(5,'COMP',3380,'Refactoring databases',59,'Refactoring has proven its value in a wide range of development projects–helping software professionals improve system designs, maintainability, extensibility, and performance',false,10,'tintt@myumanitoba.ca');");
            ResultSet resultSet7 = statement.executeQuery("INSERT INTO POSTING VALUES(6,'COMP',3490,'Fundamentals of Computer Graphics',50,'Drawing on an impressive roster of experts in the field, Fundamentals of Computer Graphics, Fourth Edition offers an ideal resource for computer course curricula as well as a user-friendly personal or professional reference.',true,12,'nguyenvq@myumanitoba.ca');");
            ResultSet resultSet8 = statement.executeQuery("INSERT INTO POSTING VALUES(7,'ECONOMIC',1020,'Macroeconomia',40,'Balancing classical and Keynesian economics, this work uses the modern approach of presenting long-run analysis before short-run fluctuations, and covers the large open economy in depth',false,2,'mbluth@myumanitoba.ca');");
            ResultSet resultSet9 = statement.executeQuery("INSERT INTO POSTING VALUES(8,'ECONOMIC',1010,'Freakonomics',155,'A Rogue Economist Explores the Hidden Side of Everything is the debut non-fiction book by University of Chicago economist Steven Levitt and New York Times journalist Stephen J. Dubner',true,3,'tranvt@myumanitoba.ca');");
            ResultSet resultSet10 = statement.executeQuery("INSERT INTO POSTING VALUES(9,'ENG',3130,'The Design of Everyday Things',200,'The Design of Everyday Things is a best-selling book by cognitive scientist and usability engineer Donald Norman about how design serves as the communication between object and user, and how to optimize that conduit of communication in order to make the experience of using the object pleasurable.',false,10,'mbluth@myumanitoba.ca');");

            ResultSet resultSet11 = statement.executeQuery("INSERT INTO POSTING VALUES(10,'HIS',20,'The look at previous spells',28,'Good condition. Not used',false,2,'pharry@hogwiz.uk');");
            ResultSet resultSet12 = statement.executeQuery("INSERT INTO POSTING VALUES(11,'PLA',15,'How to excel in herbology',10,'Used, some torn pages',true,38,'lucilleb@hogwiz.uk');");
            ResultSet resultSet13 = statement.executeQuery("INSERT INTO POSTING VALUES(12,'MAG',10,'Fly to the sky',200,'New book, packed',false,4,'ronweasley@hogwiz.uk');");
            ResultSet resultSet14 = statement.executeQuery("INSERT INTO POSTING VALUES(13,'MAG',15,'Textbook of Half-blood Prince',400,'Old textbook, some new potions and spells',true,40,'pharry@hogwiz.uk');");
            ResultSet resultSet15 = statement.executeQuery("INSERT INTO POSTING VALUES(14,'FUT',35,'Intro to Divination',40,'Used but still in great condition, has notes in it',true,4,'pharry@hogwiz.uk');");
            ResultSet resultSet16 = statement.executeQuery("INSERT INTO POSTING VALUES(15,'FUT',35,'Intro to Divination',165,'New book, still packed',false,4,'ronweasley@hogwiz.uk');");

            ResultSet resultSet17 = statement.executeQuery("INSERT INTO POSTING VALUES(16,'BIZ',1120,'The E Myth',40,'An instant classic, this revised and updated edition of the phenomenal bestseller dispels the myths about starting your own business.',false,8,'jaydee@myrcc.ca');");
            ResultSet resultSet18 = statement.executeQuery("INSERT INTO POSTING VALUES(17,'BIZ',2120,'Zero to One',15,': Notes on Startups, or How to Build the Future is a 2014 book by the American entrepreneur and investor Peter Thiel co-written with Blake Masters',true,10,'christ@myrcc.ca');");
            ResultSet resultSet19 = statement.executeQuery("INSERT INTO POSTING VALUES(18,'HOSP',1020,'The Heart of Hospitality: Great Hotel and Restaurant Leaders Share Their Secrets',105,'Success in today’s rapidly changing hospitality industry depends on understanding the desires of guests of all ages, from seniors and boomers to the newly dominant millennial generation of travelers',false,2,'kelsorob@myrcc.ca');");
            ResultSet resultSet20 = statement.executeQuery("INSERT INTO POSTING VALUES(19,'COMS',1450,'Seeing Voices',50,'A Journey Into the World of the Deaf is a 1989 book by neurologist Oliver Sacks. The book covers a variety of topics in Deaf studies, including sign language, the neurology of deafness, the history of the treatment of Deaf Americans, and linguistic and social challenges facing the Deaf community',true,5,'coxperry@myrcc.ca');");
            ResultSet resultSet21 = statement.executeQuery("INSERT INTO POSTING VALUES(20,'COMS',1780,'A Child and Youth Care Approach to Working with Families',250,'Use this newly developed family-oriented approach to be a better youth worker! In A Child and Youth Care Approach to Working with Families, practitioners and trainers in a new child methodology show you how to expand your youth program to involve family work using the Child and Youth Care Approach.Use this newly developed family-oriented approach to be a better youth worker! In A Child and Youth Care Approach to Working with Families, practitioners and trainers in a new child methodology show you how to expand your youth program to involve family work using the Child and Youth Care Approach.',false,1,'jaydee@myrcc.ca');");

            ResultSet resultSet22 = statement.executeQuery("INSERT INTO POSTING VALUES(21,'ANTH',1000,'Guns, Germs, and Steel',10,'Guns, Germs, and Steel: The Fates of Human Societies is a 1997 transdisciplinary non-fiction book by Jared Diamond, professor of geography and physiology at the University of California, Los Angeles',true,19,'jwatson@oxoxox.uk');");
            ResultSet resultSet23 = statement.executeQuery("INSERT INTO POSTING VALUES(22,'ANTH',2000,'Coming of Age in Samoa',260,'Coming of Age in Samoa is a book by American anthropologist Margaret Mead based upon her research and study of youth – primarily adolescent girls – on the island of Ta in the Samoan Islands. ',false,2,'adlerire@oxoxox.uk');");
            ResultSet resultSet24 = statement.executeQuery("INSERT INTO POSTING VALUES(23,'ERTH',1500,'A Short History of Nearly Everything ',50,'A  Short History of Nearly Everything by American-British author Bill Bryson is a popular science book that explains some areas of science, using easily accessible language that appeals more to the general public than many other books dedicated to the subject.',false,8,'jwatson@oxoxox.uk');");
            ResultSet resultSet25 = statement.executeQuery("INSERT INTO POSTING VALUES(24,'LAW',1100,'The New Jim Crow: Mass Incarceration in the Age of Colorblindness',75,'The New Jim Crow: Mass Incarceration in the Age of Colorblindness is a book by Michelle Alexander, a civil rights litigator and legal scholar.',true,7,'sherlock@oxoxox.uk');");
            ResultSet resultSet26 = statement.executeQuery("INSERT INTO POSTING VALUES(25,'LAW',1600,'Learning Roman criminal law',40,'This comprehensive text provides vital background information and a coherent structure for understanding the law.',true,10,'adlerire@oxoxox.uk');");
            ResultSet resultSet27 = statement.executeQuery("INSERT INTO POSTING VALUES(26,'LAW',1100,'Criminal Procedure',150,'Structured chronologically, the relevant rules and procedures are introduced and explained in the same sequence as the criminal process, offering a logical and intuitive organization.',false,1,'sherlock@oxoxox.uk');");
            ResultSet resultSet28 = statement.executeQuery("INSERT INTO POSTING VALUES(27,'LAW',2700,'Smith & Hogan Criminal Law',60,'Celebrating fifty years since it first published in 1965, Smith & Hogan Criminal Law is rightly regarded as the leading doctrinal textbook on criminal law in England and Wales.',false,4,'jwatson@oxoxox.uk');");

            resultSet1.close();
            resultSet2.close();
            resultSet3.close();
            resultSet4.close();
            resultSet5.close();
            resultSet6.close();
            resultSet7.close();
            resultSet8.close();
            resultSet9.close();
            resultSet10.close();
            resultSet11.close();
            resultSet12.close();
            resultSet13.close();
            resultSet14.close();
            resultSet15.close();
            resultSet16.close();
            resultSet17.close();
            resultSet18.close();
            resultSet19.close();
            resultSet20.close();
            resultSet21.close();
            resultSet22.close();
            resultSet23.close();
            resultSet24.close();
            resultSet25.close();
            resultSet26.close();
            resultSet27.close();
            resultSet28.close();
        }
        catch (Exception e)
        {
            System.out.println("Error in inserting posting values.");
            e.printStackTrace();
        }
    }

    private void insertRatings()
    {
        try
        {
            ResultSet resultSet1 = statement.executeQuery("INSERT INTO RATING VALUES('adlerire@oxoxox.uk',1.00,'lucilleb@hogwiz.uk');");
            ResultSet resultSet2 = statement.executeQuery("INSERT INTO RATING VALUES('adlerire@oxoxox.uk',3.00,'pharry@hogwiz.uk');");
            ResultSet resultSet3 = statement.executeQuery("INSERT INTO RATING VALUES('adlerire@oxoxox.uk',5.00,'ronweasley@hogwiz.uk');");
            resultSet1.close();
            resultSet2.close();
            resultSet3.close();
        }
        catch (Exception e)
        {
            System.out.println("Error in inserting institution values.");
            e.printStackTrace();
        }
    }

    private void closeConnection()
    {
        try
        {
            ResultSet resultSet = statement.executeQuery("SHUTDOWN");
            resultSet.close();
        }
        catch (Exception e)
        {
            System.out.println("Could not close connection.");
            e.printStackTrace();
        }
    }
}
