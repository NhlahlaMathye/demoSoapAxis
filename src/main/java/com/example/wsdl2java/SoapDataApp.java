package com.example.wsdl2java;


import org.apache.axis2.AxisFault;
import org.junit.Assert;
import org.oorsprong.www.websamples_countryinfo.ArrayOftCountryCodeAndName;
import org.oorsprong.www.websamples_countryinfo.ListOfCountryNamesByNameDocument;
import org.oorsprong.www.websamples_countryinfo.ListOfCountryNamesByNameResponseDocument;

public class SoapDataApp {

    public static void main(String[] args) throws Exception {

        CountryInfoServiceStub stub = null;
        try {
                stub = new CountryInfoServiceStub();
        }catch (AxisFault e){
            e.printStackTrace();
            Assert.fail();
        }
        Assert.assertNotNull(stub);

        ListOfCountryNamesByNameDocument requestDoc = ListOfCountryNamesByNameDocument.Factory.newInstance();
        ListOfCountryNamesByNameDocument.ListOfCountryNamesByName request = ListOfCountryNamesByNameDocument.ListOfCountryNamesByName.Factory.newInstance();

        requestDoc.setListOfCountryNamesByName(request);
        ListOfCountryNamesByNameResponseDocument responseDocument = stub.listOfCountryNamesByName(requestDoc);

        assert responseDocument != null;
        ListOfCountryNamesByNameResponseDocument.ListOfCountryNamesByNameResponse response = responseDocument.getListOfCountryNamesByNameResponse();

        assert response != null;

        ArrayOftCountryCodeAndName result = response.getListOfCountryNamesByNameResult();
        assert result != null;

        for (int x = 0; x < result.sizeOfTCountryCodeAndNameArray(); x++)
        {
            String name = result.getTCountryCodeAndNameArray(x).getSName();
            String code = result.getTCountryCodeAndNameArray(x).getSISOCode();
            System.out.println("Country Name: " + name + "\nCountry Code:"+code+"\n");
        }

        System.out.println();
    }
}
