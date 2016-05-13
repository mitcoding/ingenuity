//
//  ScatterChartViewController.swift
//  iOSChartsDemo
//
//  Created by guest on 5/10/16.
//  Copyright © 2016 Appcoda. All rights reserved.
//

import UIKit
import Charts

class ABcontroller: UIViewController {

    //@IBOutlet var abSlider: UISlider!
    //@IBOutlet var ambalABvalue: UISlider!
    //@IBOutlet var abValue: UILabel!
    //@IBOutlet var ambalABlabel: UILabel!
    @IBOutlet var latestSliderValue: UISlider!
    
    @IBOutlet var latestLabelValue: UILabel!
    @IBAction func abValueChanged(sender: UISlider) {
        
        let selectedValue = Float(sender.value)
        
        latestLabelValue.text = String(stringInterpolationSegment: selectedValue) //+ "% to new content"
    }
    //var days: [String]!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
    }
    

    
    
}
