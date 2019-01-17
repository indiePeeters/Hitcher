//
//  NavigationPanelViewController.swift
//  hitcher
//
//  Created by issd on 27/12/2018.
//  Copyright © 2018 fhict. All rights reserved.
//

import Foundation
import UIKit

class NavigationPanelViewController: UIViewController {
    
    //Parent ViewControllers
    
    @IBOutlet var lbl_Destination: UILabel!
    @IBOutlet var lbl_AverageTime: UILabel!
    
    
    @IBAction func btn_startNavigation_clicked(_ sender: Any) {
        if let selectedHitchhotspot = self.parent{
            
        }
    }
}
