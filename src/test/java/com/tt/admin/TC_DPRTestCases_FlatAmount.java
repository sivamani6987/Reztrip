package com.tt.admin;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.tt.BE.pom.CallCenterBookings;
import com.tt.BE.pom.ClasicFlow;
import com.tt.BE.pom.ClassicFlow_AvailPage;
import com.tt.BE.pom.ClassicFlow_ReservationDetailsPage;
import com.tt.admin.pom.AlternateProperties;
import com.tt.admin.pom.Dynamic_Pricing_Rules;

import com.tt.utility.BaseTest;
import com.tt.utility.ReadDataFromExcel;
import com.tt.utility.SeleniumRepo;

public class TC_DPRTestCases_FlatAmount extends BaseTest
{
	Logger logger = Logger.getLogger(TC_DPRTestCases_FlatAmount.class);
	String RuleName ;
	String RuleCode ;
	String ShortDescription ;
	String FlatAmount ;
	String Percentage;
	String Errors ;
	String SteadyDays ;
	String SteadyFlatAmount ;
	String NthNight;
	String DOW_ALL;
	String DOW_AnyDay;
	String EnterCurrency ;
	String Enterrooms;
	String roomtype;
	String FirstName;
	String LastName;
	String Email;
	String Cardname;
	String Numberoncard;
	String Entercountry;
	String Address;
	String Address2;
	String Entercity;
	String Enterstate;
	String Enterzipcode;
	String Enterphonenum;
	String RatePlan;
	float Discountprice;
	String CallCenterUser;
	String Adults;
	String Children;
	String RateType;

	@DataProvider
	public Object[][] DPR() throws IOException
	{
		Object[][] testObjArray = ReadDataFromExcel.readExcelAsMap(System.getProperty("user.dir") + "\\TestData\\AdminTestData\\Admin_TestData_qa2.xlsx", "DPR");

		return (testObjArray);
	}
	@DataProvider
	public Object[][] ClasicFlow() throws IOException 
	{

		Object[][] testObjArray = ReadDataFromExcel.readExcelAsMap(
				System.getProperty("user.dir") + "\\TestData\\BE_TestData.xlsx", "ClasicFlow");

		return (testObjArray);
	}
	@DataProvider
	public Object[][] CallCenter() throws IOException {

		Object[][] testObjArray = ReadDataFromExcel.readExcelAsMap(
				System.getProperty("user.dir") + "\\TestData\\BE_TestData.xlsx", "CallCenter_bookings");

		return (testObjArray);
	}
	@Test(dataProvider="DPR",enabled=true,priority=1)
	public void AddNewDPR_Discounttype_FlatAmount_DOW_AllDays(HashMap<String, String> h)
	{
		try 
		{
			login_Brand_Property();
			
			//SeleniumRepo.driver.switchTo().window(ParentWindow);

			Dynamic_Pricing_Rules DPR =PageFactory.initElements(SeleniumRepo.driver, Dynamic_Pricing_Rules.class);
			


			test = extent.createTest("Add New DPR Using:DISCOUNT DEFINITION:Discount type: Flat Amount :All Days");
			logger.info("Add New DPR Using:DISCOUNT DEFINITION:Discount type: Flat Amount :All Days");


			RuleName = h.get("RuleName").toString().trim();
			RuleCode = h.get("RuleCode").toString().trim();
			ShortDescription = h.get("ShortDescription").toString().trim();
			FlatAmount = h.get("FlatAmount").toString().trim();
			Percentage = h.get("Precentage").toString().trim();
			Errors = h.get("Errors").toString().trim();
			SteadyDays = h.get("SteadyDays").toString().trim();
			SteadyFlatAmount = h.get("SteadyFlatAmount").toString().trim();
			NthNight = h.get("NthNight").toString().trim();
			RatePlan = h.get("RatePlan").toString().trim();
			DOW_ALL = h.get("DOW_ALL").toString().trim();
			DOW_AnyDay = h.get("DOW_AnyDay").toString().trim();

			DPR.ReuseDPRFlat(RuleName,RuleCode,ShortDescription,"Flat Amount", "Day Of Week", FlatAmount, SteadyDays, SteadyFlatAmount,DOW_ALL,RatePlan,NthNight,Percentage);

		}
		catch (Exception e) 
		{
			logger.error("unable to "+getClass().getEnclosingMethod().getName() + e);
			test.log(Status.ERROR, "unable to "+getClass().getEnclosingMethod().getName() + e);
			e.printStackTrace();
			Assert.assertEquals(false, true);
		}

	}
	@Test(dataProvider="ClasicFlow",description="Verify in BE",enabled=true,priority=2)
	public void VerfiyinBE_AddNewDPR_Discounttype_FlatAmount_DOW(HashMap<String, String> h2) throws ParseException, IOException
	{

		ClasicFlow  clasicflow =PageFactory.initElements(SeleniumRepo.driver, ClasicFlow.class);
		ClassicFlow_AvailPage  classicFlowAvailPage = PageFactory.initElements(SeleniumRepo.driver, ClassicFlow_AvailPage.class);
		ClassicFlow_ReservationDetailsPage  classicFlowReservationDet = PageFactory.initElements(SeleniumRepo.driver, ClassicFlow_ReservationDetailsPage.class);
		Dynamic_Pricing_Rules DPR =PageFactory.initElements(SeleniumRepo.driver, Dynamic_Pricing_Rules.class);

		try 
		{

			EnterCurrency = h2.get("EnterCurrency").toString().trim();
			Enterrooms = h2.get("Enterrooms").toString().trim();
			roomtype = h2.get("roomtype").toString().trim();
			FirstName = h2.get("FirstName").toString().trim();
			LastName = h2.get("LastName").toString().trim();
			Email = h2.get("Email").toString().trim();
			Cardname = h2.get("Cardname").toString().trim();
			Numberoncard = h2.get("Numberoncard").toString().trim();
			Entercountry = h2.get("Entercountry").toString().trim();
			Address = h2.get("Address").toString().trim();
			Address2 = h2.get("Address2").toString().trim();
			Entercity = h2.get("Entercity").toString().trim();
			Enterstate = h2.get("Enterstate").toString().trim();
			Enterzipcode = h2.get("Enterzipcode").toString().trim();
			Enterphonenum = h2.get("Enterphonenum").toString().trim();
            Thread.sleep(5000);
			windowid();

			SeleniumRepo.driver.switchTo().window(NewWindow);

			//clasicflow.ReuseMethodsforDPR(Enterrooms, roomtype, RatePlan,DPR.finalDay_Month_Date,RatePlan,FlatAmount,ShortDescription,SteadyDays,"Day Of Week",NthNight,"FlatAmount",FlatAmount,FirstName,LastName,Email,Cardname,Numberoncard,Entercountry,Address, Enterzipcode, Address2, Enterstate, Entercity,Enterphonenum);
			
			clasicflow.ReuseMethodsforDPRstart();
			classicFlowAvailPage.ReuseMethodsforDPRcheckavailabitlity(Enterrooms, roomtype);
			classicFlowReservationDet.ReuseMethodsforDPRsetupResDetails(RatePlan);
			clasicflow.ReuseMethodsforDPRclose(RatePlan,DPR.finalDay_Month_Date,RatePlan,FlatAmount,ShortDescription,SteadyDays,"Day Of Week",NthNight,"FlatAmount",FlatAmount,FirstName,LastName,Email,Cardname,Numberoncard,Entercountry,Address, Enterzipcode, Address2, Enterstate, Entercity,Enterphonenum);
			
			
			//clasicflow.verify_Strike(DPR.finalDay_Month_Date,RatePlan,FlatAmount,Discountprice,ShortDescription,SteadyDays,"Day Of Week",NthNight,"FlatAmount",FlatAmount);
			
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.assertEquals(false, true);
		}
		
	}
	@Test(dataProvider="DPR",priority=3,enabled=true)
	public void AddNewDPR_Discounttype_FlatAmount_DOW_particularDay(HashMap<String, String> h)
	{
		try 
		{
			
			Dynamic_Pricing_Rules DPR =PageFactory.initElements(SeleniumRepo.driver, Dynamic_Pricing_Rules.class);
			
			ClasicFlow  clasicflow =PageFactory.initElements(SeleniumRepo.driver, ClasicFlow.class);
			ClassicFlow_AvailPage  classicFlowAvailPage = PageFactory.initElements(SeleniumRepo.driver, ClassicFlow_AvailPage.class);
			ClassicFlow_ReservationDetailsPage  classicFlowReservationDet = PageFactory.initElements(SeleniumRepo.driver, ClassicFlow_ReservationDetailsPage.class);


			test = extent.createTest("Add New DPR Discount type: Flat Amount : Day Of Week :particular Day");
			logger.info("Add New DPR Discount type: Flat Amount : Day Of Week :particular Day");

			SeleniumRepo.driver.switchTo().window(ParentWindow);

			Discountprice=DPR.ReuseDPRFlat(RuleName,RuleCode,ShortDescription,"Flat Amount", "Day Of Week", FlatAmount, SteadyDays, SteadyFlatAmount,DOW_AnyDay,RatePlan,NthNight,Percentage);
			
			SeleniumRepo.driver.switchTo().window(NewWindow);
			
			System.out.println(DPR.finalDay);
			
			//clasicflow.ReuseMethodsforDPR(Enterrooms, roomtype, RatePlan,DPR.finalDay_Month_Date,RatePlan,FlatAmount,ShortDescription,SteadyDays,"AnyDay",NthNight,"FlatAmount",FlatAmount,FirstName,LastName,Email,Cardname,Numberoncard,Entercountry,Address, Enterzipcode, Address2, Enterstate, Entercity,Enterphonenum);
			//clasicflow.verify_Strike(DPR.finalDay_Month_Date,RatePlan,FlatAmount,Discountprice,ShortDescription,SteadyDays,"AnyDay",NthNight,"FlatAmount",FlatAmount);
			clasicflow.ReuseMethodsforDPRstart();
			classicFlowAvailPage.ReuseMethodsforDPRcheckavailabitlity(Enterrooms, roomtype);
			classicFlowReservationDet.ReuseMethodsforDPRsetupResDetails(RatePlan);
			clasicflow.ReuseMethodsforDPRclose(RatePlan,DPR.finalDay_Month_Date,RatePlan,FlatAmount,ShortDescription,SteadyDays,"Day Of Week",NthNight,"FlatAmount",FlatAmount,FirstName,LastName,Email,Cardname,Numberoncard,Entercountry,Address, Enterzipcode, Address2, Enterstate, Entercity,Enterphonenum);
			
			
		}
		catch (Exception e) 
		{
			logger.error("unable to "+getClass().getEnclosingMethod().getName() + e);
			test.log(Status.ERROR, "unable to "+getClass().getEnclosingMethod().getName() + e);
			e.printStackTrace();
			//Assert.assertEquals(false, true);
		}
	}

	@Test(dataProvider="DPR",priority=4,enabled=true)
	public void AddNewDPR_Discounttype_FlatAmount_First_N_Nights(HashMap<String, String> h)
	{
		try 
		{
			
			Dynamic_Pricing_Rules DPR =PageFactory.initElements(SeleniumRepo.driver, Dynamic_Pricing_Rules.class);
			
			ClasicFlow  clasicflow =PageFactory.initElements(SeleniumRepo.driver, ClasicFlow.class);
			ClassicFlow_AvailPage  classicFlowAvailPage = PageFactory.initElements(SeleniumRepo.driver, ClassicFlow_AvailPage.class);
			ClassicFlow_ReservationDetailsPage  classicFlowReservationDet = PageFactory.initElements(SeleniumRepo.driver, ClassicFlow_ReservationDetailsPage.class);


			test = extent.createTest("Add New DPR Discount type: Flat Amount :First N Nights");
			logger.info("Add New DPR Discount type: Flat Amount : Day Of Week :First N Nights");

			SeleniumRepo.driver.switchTo().window(ParentWindow);

			DPR.ReuseDPRFlat(RuleName,RuleCode,ShortDescription,"Flat Amount", "First N Nights", FlatAmount, SteadyDays, SteadyFlatAmount,DOW_AnyDay,RatePlan,NthNight,Percentage);
			
			SeleniumRepo.driver.switchTo().window(NewWindow);
			
			//clasicflow.ReuseMethodsforDPR(Enterrooms, roomtype, RatePlan,DPR.finalDay_Month_Date,RatePlan,SteadyFlatAmount,ShortDescription,SteadyDays,"First N Nights",NthNight,"FlatAmount",FlatAmount,FirstName,LastName,Email,Cardname,Numberoncard,Entercountry,Address, Enterzipcode, Address2, Enterstate, Entercity,Enterphonenum);
			//clasicflow.verify_Strike(DPR.finalDay_Month_Date,RatePlan,SteadyFlatAmount,Discountprice,ShortDescription,SteadyDays,"First N Nights",NthNight,"FlatAmount",FlatAmount);
			clasicflow.ReuseMethodsforDPRstart();
			classicFlowAvailPage.ReuseMethodsforDPRcheckavailabitlity(Enterrooms, roomtype);
			classicFlowReservationDet.ReuseMethodsforDPRsetupResDetails(RatePlan);
			clasicflow.ReuseMethodsforDPRclose(RatePlan,DPR.finalDay_Month_Date,RatePlan,FlatAmount,ShortDescription,SteadyDays,"Day Of Week",NthNight,"FlatAmount",FlatAmount,FirstName,LastName,Email,Cardname,Numberoncard,Entercountry,Address, Enterzipcode, Address2, Enterstate, Entercity,Enterphonenum);
			
			
		}
		catch (Exception e) 
		{
			logger.error("unable to "+getClass().getEnclosingMethod().getName() + e);
			test.log(Status.ERROR, "unable to "+getClass().getEnclosingMethod().getName() + e);
			e.printStackTrace();
			//Assert.assertEquals(false, true);
		}
	}
	@Test(dataProvider="DPR",priority=5,enabled=true)
	public void AddNewDPR_Discounttype_FlatAmount_Last_N_Nights(HashMap<String, String> h)
	{
		try 
		{
			
			Dynamic_Pricing_Rules DPR =PageFactory.initElements(SeleniumRepo.driver, Dynamic_Pricing_Rules.class);
			
			ClasicFlow  clasicflow =PageFactory.initElements(SeleniumRepo.driver, ClasicFlow.class);
			ClassicFlow_AvailPage  classicFlowAvailPage = PageFactory.initElements(SeleniumRepo.driver, ClassicFlow_AvailPage.class);
			ClassicFlow_ReservationDetailsPage  classicFlowReservationDet = PageFactory.initElements(SeleniumRepo.driver, ClassicFlow_ReservationDetailsPage.class);



			test = extent.createTest("Add New DPR Discount type: Flat Amount :Last N Nights");
			logger.info("Add New DPR Discount type: Flat Amount : Day Of Week :Last N Nights");

			SeleniumRepo.driver.switchTo().window(ParentWindow);
			
			DPR.ReuseDPRFlat(RuleName,RuleCode,ShortDescription,"Flat Amount", "Last N Nights", FlatAmount, SteadyDays, SteadyFlatAmount,DOW_AnyDay,RatePlan,NthNight,Percentage);
			SeleniumRepo.driver.switchTo().window(NewWindow);
			
			//clasicflow.ReuseMethodsforDPR(Enterrooms, roomtype, RatePlan,DPR.finalDay_Month_Date,RatePlan,SteadyFlatAmount,ShortDescription,SteadyDays,"Last N Nights",NthNight,"FlatAmount",FlatAmount,FirstName,LastName,Email,Cardname,Numberoncard,Entercountry,Address, Enterzipcode, Address2, Enterstate, Entercity,Enterphonenum);
			//clasicflow.verify_Strike(DPR.finalDay_Month_Date,RatePlan,SteadyFlatAmount,Discountprice,ShortDescription,SteadyDays,"Last N Nights",NthNight,"FlatAmount",FlatAmount);	
			clasicflow.ReuseMethodsforDPRstart();
			classicFlowAvailPage.ReuseMethodsforDPRcheckavailabitlity(Enterrooms, roomtype);
			classicFlowReservationDet.ReuseMethodsforDPRsetupResDetails(RatePlan);
			clasicflow.ReuseMethodsforDPRclose(RatePlan,DPR.finalDay_Month_Date,RatePlan,FlatAmount,ShortDescription,SteadyDays,"Day Of Week",NthNight,"FlatAmount",FlatAmount,FirstName,LastName,Email,Cardname,Numberoncard,Entercountry,Address, Enterzipcode, Address2, Enterstate, Entercity,Enterphonenum);
			
		
		}
		catch (Exception e) 
		{
			logger.error("unable to "+getClass().getEnclosingMethod().getName() + e);
			test.log(Status.ERROR, "unable to "+getClass().getEnclosingMethod().getName() + e);
			e.printStackTrace();
			//Assert.assertEquals(false, true);
		}
	}
	@Test(dataProvider="DPR",priority=6,enabled=true)
	public void AddNewDPR_Discounttype_FlatAmount_NthNight(HashMap<String, String> h)
	{
		try 
		{
			
			Dynamic_Pricing_Rules DPR =PageFactory.initElements(SeleniumRepo.driver, Dynamic_Pricing_Rules.class);
			
			ClasicFlow  clasicflow =PageFactory.initElements(SeleniumRepo.driver, ClasicFlow.class);
			ClassicFlow_AvailPage  classicFlowAvailPage = PageFactory.initElements(SeleniumRepo.driver, ClassicFlow_AvailPage.class);
			ClassicFlow_ReservationDetailsPage  classicFlowReservationDet = PageFactory.initElements(SeleniumRepo.driver, ClassicFlow_ReservationDetailsPage.class);



			test = extent.createTest("Add New DPR Discount type: Flat Amount :Nth Night");
			logger.info("Add New DPR Discount type: Flat Amount : Day Of Week :Nth Night");

			SeleniumRepo.driver.switchTo().window(ParentWindow);

			DPR.ReuseDPRFlat(RuleName,RuleCode,ShortDescription,"Flat Amount", "Nth Night", FlatAmount, SteadyDays, SteadyFlatAmount,DOW_AnyDay,RatePlan,NthNight,Percentage);
			
			SeleniumRepo.driver.switchTo().window(NewWindow);
			
			//clasicflow.ReuseMethodsforDPR(Enterrooms, roomtype, RatePlan,DPR.finalDay_Month_Date,RatePlan,SteadyFlatAmount,ShortDescription,SteadyDays,"Nth Night",NthNight,"FlatAmount",FlatAmount,FirstName,LastName,Email,Cardname,Numberoncard,Entercountry,Address, Enterzipcode, Address2, Enterstate, Entercity,Enterphonenum);
			//clasicflow.verify_Strike(DPR.finalDay_Month_Date,RatePlan,SteadyFlatAmount,Discountprice,ShortDescription,SteadyDays,"Nth Night",NthNight,"FlatAmount",FlatAmount);
			clasicflow.ReuseMethodsforDPRstart();
			classicFlowAvailPage.ReuseMethodsforDPRcheckavailabitlity(Enterrooms, roomtype);
			classicFlowReservationDet.ReuseMethodsforDPRsetupResDetails(RatePlan);
			clasicflow.ReuseMethodsforDPRclose(RatePlan,DPR.finalDay_Month_Date,RatePlan,FlatAmount,ShortDescription,SteadyDays,"Day Of Week",NthNight,"FlatAmount",FlatAmount,FirstName,LastName,Email,Cardname,Numberoncard,Entercountry,Address, Enterzipcode, Address2, Enterstate, Entercity,Enterphonenum);
			
			
		}
		catch (Exception e) 
		{
			logger.error("unable to "+getClass().getEnclosingMethod().getName() + e);
			test.log(Status.ERROR, "unable to "+getClass().getEnclosingMethod().getName() + e);
			e.printStackTrace();
			//Assert.assertEquals(false, true);
		}
	}
	@Test(dataProvider="DPR",priority=7,enabled=true)
	public void AddNewDPR_Discounttype_FlatAmount_AllNights(HashMap<String, String> h)
	{
		try 
		{
			
			Dynamic_Pricing_Rules DPR =PageFactory.initElements(SeleniumRepo.driver, Dynamic_Pricing_Rules.class);
			
			ClasicFlow  clasicflow =PageFactory.initElements(SeleniumRepo.driver, ClasicFlow.class);
			ClassicFlow_AvailPage  classicFlowAvailPage = PageFactory.initElements(SeleniumRepo.driver, ClassicFlow_AvailPage.class);
			ClassicFlow_ReservationDetailsPage  classicFlowReservationDet = PageFactory.initElements(SeleniumRepo.driver, ClassicFlow_ReservationDetailsPage.class);



			test = extent.createTest("Add New DPR Discount type: Flat Amount :All Nights");
			logger.info("Add New DPR Discount type: Flat Amount : Day Of Week :All Nights");

			SeleniumRepo.driver.switchTo().window(ParentWindow);

			
			DPR.ReuseDPRFlat(RuleName,RuleCode,ShortDescription,"Flat Amount", "All Nights", FlatAmount, SteadyDays, SteadyFlatAmount,DOW_AnyDay,RatePlan,NthNight,Percentage);
			
			SeleniumRepo.driver.switchTo().window(NewWindow);
			
			
			//clasicflow.ReuseMethodsforDPR(Enterrooms, roomtype, RatePlan,DPR.finalDay_Month_Date,RatePlan,SteadyFlatAmount,ShortDescription,SteadyDays,"All Nights",NthNight,"FlatAmount",FlatAmount,FirstName,LastName,Email,Cardname,Numberoncard,Entercountry,Address, Enterzipcode, Address2, Enterstate, Entercity,Enterphonenum);
			clasicflow.ReuseMethodsforDPRstart();
			classicFlowAvailPage.ReuseMethodsforDPRcheckavailabitlity(Enterrooms, roomtype);
			classicFlowReservationDet.ReuseMethodsforDPRsetupResDetails(RatePlan);
			clasicflow.ReuseMethodsforDPRclose(RatePlan,DPR.finalDay_Month_Date,RatePlan,FlatAmount,ShortDescription,SteadyDays,"Day Of Week",NthNight,"FlatAmount",FlatAmount,FirstName,LastName,Email,Cardname,Numberoncard,Entercountry,Address, Enterzipcode, Address2, Enterstate, Entercity,Enterphonenum);
			
		
		}
		catch (Exception e) 
		{
			logger.error("unable to "+getClass().getEnclosingMethod().getName() + e);
			test.log(Status.ERROR, "unable to "+getClass().getEnclosingMethod().getName() + e);
			e.printStackTrace();
			//Assert.assertEquals(false, true);
		}
	}
	@Test(dataProvider="CallCenter",priority=8,enabled=true)
	public void VerifyDPR_In_CallCenter(HashMap<String, String> h)
	{
		try 
		{
			
			Dynamic_Pricing_Rules DPR =PageFactory.initElements(SeleniumRepo.driver, Dynamic_Pricing_Rules.class);
			
			CallCenterBookings  CallCenter=PageFactory.initElements(SeleniumRepo.driver, CallCenterBookings.class);
			
			
			CallCenterUser = h.get("CallCenterUser").toString().trim();
			Adults = h.get("Adults").toString().trim();
			Children = h.get("Children").toString().trim();
			RateType=h.get("RateType").toString().trim();
			String Enterrooms = h.get("Enterrooms").toString().trim();
			

			test = extent.createTest("verify that the call center ones show up, and you can proceed make a booking.");
			logger.info("verify that the call center ones show up, and you can proceed make a booking.");
			
			CallCenter.GoToUrl();
			CallCenter.Call_Center_User(CallCenterUser);
			CallCenter.departuredate();
			CallCenter.arrival_date();
			
			CallCenter.select_rooms(Enterrooms);
			CallCenter.select_adults(Adults);
			CallCenter.select_Children(Children);

			CallCenter.Check_Availability();
			CallCenter.Select_roomtype(roomtype, Enterrooms,RateType);
			CallCenter.payment();
			CallCenter.verify_Strike("Flat Amount", "Day Of Week", FlatAmount, SteadyDays, SteadyFlatAmount,DOW_ALL,RatePlan,NthNight,Percentage);
			SeleniumRepo.driver.close();
			
		}
		catch (Exception e) 
		{
			logger.error("unable to "+getClass().getEnclosingMethod().getName() + e);
			test.log(Status.ERROR, "unable to "+getClass().getEnclosingMethod().getName() + e);
			e.printStackTrace();
			//Assert.assertEquals(false, true);
		}
	}
	
	@Test(dataProvider="DPR",enabled=true,priority=9)
	public void Copy_DPR_Errors(HashMap<String, String> h)
	{
		try 
		{
			
			SeleniumRepo.driver.switchTo().window(ParentWindow);

			Dynamic_Pricing_Rules DPR =PageFactory.initElements(SeleniumRepo.driver, Dynamic_Pricing_Rules.class);
			AlternateProperties AlternateProperties = PageFactory.initElements(SeleniumRepo.driver, AlternateProperties.class);
			


			test = extent.createTest("Copy DPR Errors");
			logger.info("Copy DPR Errors");


			DPR.Click_DPRListAll();
			DPR.Click_CopyDPR(RuleName);

		}
		catch (Exception e) 
		{
			logger.error("unable to "+getClass().getEnclosingMethod().getName() + e);
			test.log(Status.ERROR, "unable to "+getClass().getEnclosingMethod().getName() + e);
			e.printStackTrace();
			Assert.assertEquals(false, true);
		}
	}
}
