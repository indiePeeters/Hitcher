//
//  ViewController.swift
//  IOSBasics
//
//  Created by issd on 19/12/2018.
//  Copyright © 2018 fhict. All rights reserved.
//

import UIKit
import MapKit
import CoreLocation

class HikePlannerViewController: UIViewController, CLLocationManagerDelegate, MKMapViewDelegate {
        
    //UI Bindings
    @IBOutlet weak var txt_from: UITextField!
    @IBOutlet weak var txt_to: UITextField!
    @IBOutlet var lbl_destination: UILabel!
    @IBOutlet var lbl_averageTime: UILabel!
    @IBOutlet var map: MKMapView!
    @IBOutlet var BottomPanel: UIView!
    
    //Variables
    let manager = CLLocationManager()
    let hitchhotspotRepo = HitchhotspotRepository.hitchHotspotRepository;
    var currentLocation : CLLocationCoordinate2D?
    var selectedHitchhotspot : Hitchhotspot?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.BottomPanel.isHidden = true
        manager.delegate = self
        manager.desiredAccuracy = kCLLocationAccuracyBest
        manager.requestWhenInUseAuthorization()
        manager.requestAlwaysAuthorization()
        manager.startUpdatingLocation()
        map.delegate = self
    }
    
    func locationManager(_ manager: CLLocationManager, didUpdateLocations locations: [CLLocation]) {
        let location = locations[0]
        
        //update map location
        let span:MKCoordinateSpan = MKCoordinateSpan.init(latitudeDelta: 0.1, longitudeDelta: 0.1)
        let myLocation:CLLocationCoordinate2D = CLLocationCoordinate2DMake(location.coordinate.latitude, location.coordinate.longitude)
        self.currentLocation = myLocation;
        let region:MKCoordinateRegion = MKCoordinateRegion.init(center: myLocation, span: span)
        self.map.setRegion(region, animated: true)
        self.map.showsUserLocation = true;
    }
    
    @IBAction func btn_startAdventure_clicked(_ sender: UIButton) {
        let from:String? = txt_from.text
        let to:String? = txt_to.text
        
        if(from != "" && to != ""){
            hitchhotspotRepo.getHitchhotspots(from: from!, to: to!, completion: { hitchhotspots in
                //add all results on the map as annotation
                for hitchhotspot in hitchhotspots {
                    let annotation = MKPointAnnotation();
                    annotation.coordinate = CLLocationCoordinate2DMake(hitchhotspot.latitude, hitchhotspot.longitude);
                    annotation.title = hitchhotspot.destination
                    annotation.subtitle = "hitchtime: " + String((hitchhotspot.averageTime / 60)) + " min"
                    self.map.addAnnotation(annotation);
                }
            });
        } else{
            txt_from.attributedPlaceholder = NSAttributedString(string: "Please fill in your starting location",
                                                                   attributes: [NSAttributedString.Key.foregroundColor: UIColor.init(red: 255, green: 0, blue: 0, alpha: 0.7)])
            txt_to.attributedPlaceholder = NSAttributedString(string: "Please fill in your destination",
                                                                attributes: [NSAttributedString.Key.foregroundColor: UIColor.init(red: 255, green: 0, blue: 0, alpha: 0.7)])
        }
    }
    
    @IBAction func btn_startNavigation_clicked(_ sender: UIButton) {
        if let selectedHitchhotspot = self.selectedHitchhotspot {
            selectedHitchhotspot.startNavigation();
        }
    }
    
    func mapView(_ mapView: MKMapView, didSelect view: MKAnnotationView) {
        if let destination = view.annotation!.title, let averageTimeLabel = view.annotation?.subtitle{
            let longitude = Double(view.annotation?.coordinate.longitude ?? 0)
            let latitude = Double(view.annotation?.coordinate.latitude ?? 0)
            let selectedHitchhotspot = Hitchhotspot(destination: destination!, latitude: latitude, longitude: longitude, averageTime: 0)
            
            //animate get direction panel to slide in
            self.selectedHitchhotspot = selectedHitchhotspot
            self.lbl_destination.text = destination
            self.lbl_averageTime.text = averageTimeLabel
            self.BottomPanel.isHidden = false

            UIView.animate(
                withDuration: 0.4,
                delay: 0.0,
                options: .curveLinear,
                animations: {
                    self.BottomPanel?.frame.origin.y = 500
            }) { (completed) in
                
            }
            
        }
    }
}

