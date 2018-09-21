/*}
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import domain.Attributes;
import domain.EntitySets;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author fabian
 */
public class JsonAdmin {
    
    public void readJson(String json) throws ParseException{
        ArrayList<EntitySets> entitySetsList = new ArrayList();
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(json);
        JSONObject jsonObject = (JSONObject) obj;
        JSONArray jsonArray = (JSONArray) jsonObject.get("EntitySets");
        
        for (int i = 0; i <jsonArray.size(); i++) {
            LinkedList<Attributes> attributesList = new LinkedList();
            EntitySets tempEntitySets = new EntitySets();
            JSONObject tempJsonObject = (JSONObject) jsonArray.get(i);
            tempEntitySets.setName(tempJsonObject.get("Name").toString());
            tempEntitySets.setParentEntitySet(tempJsonObject.get("ParentEntitySet").toString());
            tempEntitySets.setType(tempJsonObject.get("Type").toString());
            JSONArray jsonArrayAttributes = (JSONArray) jsonObject.get("EntitySets");
            
            for (int j = 0; j <jsonArrayAttributes.size(); j++) {
                JSONObject tempJsonObjectAttributes = (JSONObject) jsonArray.get(i);
                Attributes tempAttribute = new Attributes();
                tempAttribute.setName(tempJsonObjectAttributes.get("Name").toString());
                tempAttribute.setDomain(tempJsonObjectAttributes.get("Domain").toString());
                tempAttribute.setType(tempJsonObjectAttributes.get("Type").toString());
//                tempAttributes.setComponentList();
                tempAttribute.setIsPrimary((Boolean) tempJsonObjectAttributes.get("IsPrimary"));
                tempAttribute.setIsDiscriminator((Boolean) tempJsonObjectAttributes.get("IsDiscriminator"));
                tempAttribute.setPrecision((int)tempJsonObjectAttributes.get("Precision"));
                attributesList.add(tempAttribute);
            }
            
          tempEntitySets.setAttributes(attributesList);
          entitySetsList.add(tempEntitySets);
        }
        
        
    }  
}
