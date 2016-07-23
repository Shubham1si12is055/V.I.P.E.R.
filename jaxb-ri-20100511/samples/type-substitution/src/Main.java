/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * 
 * Copyright 1997-2007 Sun Microsystems, Inc. All rights reserved.
 * 
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License. You can obtain
 * a copy of the License at https://glassfish.dev.java.net/public/CDDL+GPL.html
 * or glassfish/bootstrap/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 * 
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at glassfish/bootstrap/legal/LICENSE.txt.
 * Sun designates this particular file as subject to the "Classpath" exception
 * as provided by Sun in the GPL Version 2 section of the License file that
 * accompanied this code.  If applicable, add the following below the License
 * Header, with the fields enclosed by brackets [] replaced by your own
 * identifying information: "Portions Copyrighted [year]
 * [name of copyright owner]"
 * 
 * Contributor(s):
 * 
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */
 
import java.io.FileInputStream;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

// import java content classes generated by binding compiler
import com.example.ipo.*;

/*
 * $Id: Main.java,v 1.2 2009/11/11 14:17:31 pavel_bucek Exp $
 */
 
public class Main {
    
    // This sample application demonstrates type substitution using
    // using schema example at http://www.w3.org/TR/xmlschema-0/#UseDerivInInstDocs.
    
    public static void main( String[] args ) {
        try {
            // create a JAXBContext capable of handling classes generated into
            // the com.example.ipo package.
            JAXBContext jc = JAXBContext.newInstance( "com.example.ipo" );
            
            // create an Unmarshaller
            Unmarshaller u = jc.createUnmarshaller();
            
            // unmarshal a po instance document into a tree of Java content
            // objects composed of classes from the "com.example.ipo" package
            JAXBElement<PurchaseOrderType>  poe = 
                (JAXBElement<PurchaseOrderType>)u.unmarshal( new FileInputStream( "ipo.xml" ) );
	    PurchaseOrderType po = poe.getValue();

            // create a Marshaller and marshal to a file
	    System.out.println("Original Purchase Order");
            Marshaller m = jc.createMarshaller();
            m.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE );
            m.marshal( poe, System.out );
	    System.out.println("******************************************************");

            // Process a return. Reverse purchase order addresses.
            Address billToAddress = po.getBillTo();
            Address shipToAddress = po.getShipTo();
	    po.setBillTo(shipToAddress);
	    po.setShipTo(billToAddress);

	    System.out.println("Return Merchandise. Ship and Bill address reversed.");
            m.marshal( poe, System.out );
	    System.out.println("******************************************************");

	    /*********************************************************************/
	    // Illustrate setting a type substitution on a javax.xml.bind.Element instance.

            USTaxExemptPurchaseOrderType uspo = 
		new ObjectFactory().createUSTaxExemptPurchaseOrderType();
	    uspo.setShipTo(billToAddress);
	    uspo.setBillTo(billToAddress);
            uspo.setTaxExemptId("charity007");
            uspo.setOrderDate(po.getOrderDate());
            uspo.setComment(po.getComment());

	    Items items = new ObjectFactory().createItems();
	    items.getItem().addAll(po.getItems().getItem());
	    uspo.setItems(items);
	    
            //PurchaseOrder element type in schema is "PurchaseOrderType".
            //Set it to an instance of type "USTaxExemptPurchaseOrderType" that
            //extends (derives using XML terminology) from "PurchaseOrderType".
	    poe.setValue(uspo);
	    System.out.println("Tax Exempt Purchase Order composed within Application.");
            m.marshal( poe, System.out );
	    System.out.println("******************************************************");

	    /*********************************************************************/
	    // Unmarshal and manipulate a global element that has a document specifed 
            // type substitution. (@xsi:type specified on element in instance document.)

            // unmarshal an instance document that identifies derived type 
	    // "ipo:USTaxExemptPurchaseOrder" for global root element <ipo:purchaseOrder>. 
            poe = (JAXBElement<PurchaseOrderType>)u.unmarshal( new FileInputStream( "ustaxexemptpo.xml" ) );

            // Access data added to element <ipo:purchaseOrder> via type substitution.
            // All data added by derivation by extension from the element's original
            // type specified in the schema must be accessed through this unwrapping
            // of the element.
            PurchaseOrderType pot = poe.getValue();
            if (poe.isTypeSubstituted() && 
		pot instanceof USTaxExemptPurchaseOrderType) {
		USTaxExemptPurchaseOrderType taxexemptpo = (USTaxExemptPurchaseOrderType)pot;
		System.out.println("US Tax exempt id: " + taxexemptpo.getTaxExemptId());
	    }

            // create a Marshaller and marshal to a file
	    System.out.println("Tax Exempt Purchase Order");
            m.marshal( poe, System.out );
	    System.out.println("******************************************************");

            
        } catch( JAXBException je ) {
            je.printStackTrace();
        } catch( IOException ioe ) {
            ioe.printStackTrace();
        } catch(Exception e) {
	    e.printStackTrace();
	}
    }
}
