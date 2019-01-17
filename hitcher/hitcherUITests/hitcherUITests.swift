//
//  hitcherUITests.swift
//  hitcherUITests
//
//  Created by issd on 23/12/2018.
//  Copyright © 2018 fhict. All rights reserved.
//

import XCTest

class hitcherUITests: XCTestCase {

    var app : XCUIApplication!
    
    override func setUp() {
        continueAfterFailure = false

        app = XCUIApplication()
        app.launch()

        // In UI tests it’s important to set the initial state - such as interface orientation - required for your tests before they run. The setUp method is a good place to do this.
    }

    override func tearDown() {
        // Put teardown code here. This method is called after the invocation of each test method in the class.
    }

    func testHikePlannerTxtFieldNotEmpty() {
        let startingpointTextField = app.textFields["Starting location"]
        startingpointTextField.tap()
        startingpointTextField.typeText("")
        
        let destinationTextField = app.textFields["Destination"]
        destinationTextField.tap()
        destinationTextField.typeText("")
        app.buttons["Start adventure"].tap()
        XCTAssertNotNil(app.textFields["Please fill in your starting location"], "Placeholder value has not updated")
        XCTAssertNotNil(app.textFields["Please fill in your destination"], "Placeholder value has not updated")
        
        // Use recording to get started writing UI tests.
        // Use XCTAssert and related functions to verify your tests produce the correct results.
    }

    func testPlaceMarkersOnMapNotEmpty(){
        let startingpointTextField = app.textFields["Starting location"]
        startingpointTextField.tap()
        startingpointTextField.typeText("Eindhoven")
        
        let destinationTextField = app.textFields["Destination"]
        destinationTextField.tap()
        destinationTextField.typeText("Amsterdam")
        app.buttons["Start adventure"].tap()
        
        sleep(2) //give app some time to get result
        
        let map = app.maps
        XCTAssertNotNil(map.element.otherElements["Amsterdam"])
    }
    
    func testNavigationPanelPlaceHolders(){
        let startingpointTextField = app.textFields["Starting location"]
        startingpointTextField.tap()
        startingpointTextField.typeText("Eindhoven")
        
        let destinationTextField = app.textFields["Destination"]
        destinationTextField.tap()
        destinationTextField.typeText("Amsterdam")
        app.buttons["Start adventure"].tap()
        
        sleep(2) //give app some time to get result
        
        let map = app.maps
        let placeMarkers = map.element.otherElements.matching(identifier: "Amsterdam")
        if(placeMarkers.count > 0){
            let firstMarker = placeMarkers.element(boundBy: 0);
            firstMarker.tap()
        }
        
        XCTAssertNotNil(app.staticTexts["Amsterdam"])
        XCTAssertNotNil(app.staticTexts["Eindhoven"])

    }
}
