package com.tt.admin;

import java.io.IOException;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.tt.admin.pom.AddNewRatePlan;
import com.tt.admin.pom.RateGrid;
import com.tt.utility.BaseTest;
import com.tt.utility.ReadDataFromExcel;
import com.tt.utility.SeleniumRepo;

public class TC_SpecialRate_dates extends BaseTest
{
	Logger logger = Logger.getLogger(this.getClass().getSimpleName());
	public static String Ratecode;

	
	@DataProvider
	public Object[][] Rategrid() throws IOException {
		Object[][] testObjArray = ReadDataFromExcel.readExcelAsMap(
				System.getProperty("user.dir") + "\\TestData\\AdminTestData\\Admin_TestData_qa2.xlsx", "Rategrid");

		return (testObjArray);
	}
	@Test(dataProvider = "Rategrid")
	public void specialrateplan(HashMap<String, String> h) throws InterruptedException, IOException 
	{

		try 
		{
			login_Brand_Property();
			RateGrid NewRategrid =PageFactory.initElements(SeleniumRepo.driver, RateGrid.class);
			AddNewRatePlan NewRatePlan =PageFactory.initElements(SeleniumRepo.driver, AddNewRatePlan.class);


			test = extent.createTest("Setting up offercode dates");
			logger.info("Setting up offercode dates");

			String EnterValue = h.get("EnterValue").toString().trim();
			String Rateplan = h.get("offer").toString().trim();
			String Category = h.get("Category").toString().trim();

			//NewRatePlan.click_Rateplan();
			NewRatePlan.click_Listall();
			NewRatePlan.Name_Search(Rateplan);
			NewRatePlan.Edit(Rateplan);
			Ratecode=NewRatePlan.Ratecode();
			NewRatePlan.Select_Category(Category);
			NewRatePlan.All_rooms();

			NewRatePlan.Book_startdate();
			NewRatePlan.Booking_Enddate();
			NewRatePlan.Checkin_startdate();
			NewRatePlan.checkout_date();
			NewRatePlan.minleadtime();
			NewRatePlan.maxleadtime();
			NewRatePlan.minLos();
			NewRatePlan.MaxLos();
			NewRatePlan.display_offers();
			NewRatePlan.click_save();

			boolean error_status=NewRatePlan.RoomsError();
			if(error_status==true)
			{
				NewRatePlan.Rooms_inventory();
				NewRatePlan.click_save();
			}

			//NewRategrid.Click_RatesandInventory();
			NewRategrid.Click_RateGrid();
			NewRategrid.Click_ManageDropdown(EnterValue);

			NewRategrid.sync_offer("BAR", Rateplan);
			NewRategrid.Click_Continue();
			NewRategrid.Enter_Startdate();
			NewRategrid.Enter_Enddate();
			NewRategrid.rateandinventory();
			NewRategrid.Click_Update();
			NewRategrid.Click_RateGrid();
			NewRategrid.Searchfield(Rateplan);
			NewRategrid.select_offer(Rateplan);
			NewRategrid.Click_Continue();
			NewRategrid.Enter_Startdate();
			NewRategrid.Enter_Enddate();
			NewRategrid.offercheck(Rateplan);
			NewRategrid.inventory();
			NewRategrid.Click_Update();
			NewRategrid.Click_RateGrid();


		}
		catch (Exception e) 
		{
			logger.error("Unable to create Rate Grid" + e);
			test.log(Status.ERROR, "Unable to create Rate Grid" + e);
			e.printStackTrace();
			Assert.assertEquals(false, true);
		}
	}
}