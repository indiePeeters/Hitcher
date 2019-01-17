//
//  hitcherTests.swift
//  hitcherTests
//
//  Created by issd on 23/12/2018.
//  Copyright © 2018 fhict. All rights reserved.
//

import XCTest

@testable import hitcher
class hitcherTests: XCTestCase {
    var hitchhotspotRepository = HitchhotspotRepository.hitchHotspotRepository;

    override func setUp() {
        // Put setup code here. This method is called before the invocation of each test method in the class.
    }

    override func tearDown() {
        // Put teardown code here. This method is called after the invocation of each test method in the class.
    }

    func testRetrieveHitchhotspots() {
        let destination = "Amsterdam"
        let startingPoint = "Utrecht"
        self.hitchhotspotRepository.getHitchhotspots(from: startingPoint, to: destination, completion: { hitchhotspots in
            XCTAssertNotNil(hitchhotspots, "There are non hitchhotspots in the firebase result")
        })
        // This is an example of a functional test case.
        // Use XCTAssert and related functions to verify your tests produce the correct results.
    }
    
    func testRetrievedHitchhotspotEqualDesination(){
        let destination = "Amsterdam"
        let startingPoint = "Utrecht"
        self.hitchhotspotRepository.getHitchhotspots(from: startingPoint, to: destination, completion: { hitchhotspots in
            for hitchhotspot in hitchhotspots {
                XCTAssertNotNil(hitchhotspot.destination, "Hitchhotspot destination appears to be nil" )
                XCTAssertEqual(hitchhotspot.destination, destination, "The Hitchhotspot destination does not equal to given destination")
            }
        })
    }
}
