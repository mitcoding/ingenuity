//
//  ScatterChartViewController.swift
//  iOSChartsDemo
//
//  Created by guest on 5/10/16.
//  Copyright © 2016 Appcoda. All rights reserved.
//

import UIKit
import Charts

class ScatterChartViewController: UIViewController {
    
    @IBOutlet weak var scatterChartView: ScatterChartView!
    
    //@IBOutlet var abSlider: UISlider!
    @IBOutlet var ambalABvalue: UISlider!
    //@IBOutlet var abValue: UILabel!
    @IBOutlet var ambalABlabel: UILabel!
    
    @IBAction func abValueChanged(sender: UISlider) {
        
        let selectedValue = Float(sender.value)
        
        ambalABlabel.text = String(stringInterpolationSegment: selectedValue) + "% to new content"
    }
    var days: [String]!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        days = ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday"]
        //var millionSold = <Double>()
        let millionsSold = [116.0, 120.0, 145.0, 155.0, 160.0]
        
        setChart(days, values: millionsSold)
        

    }
 
    func setChart(dataPoints: [String], values: [Double]) {
        var dataEntries: [ChartDataEntry] = []
        
        for i in 0..<dataPoints.count {
            let dataEntry = ChartDataEntry(value: values[i], xIndex: i)
            dataEntries.append(dataEntry)
        }
        
        let scatterChartDataSet = ScatterChartDataSet(yVals: dataEntries, label: "Page Views")
        let scatterChartData = ScatterChartData(xVals: dataPoints, dataSet: scatterChartDataSet)
        scatterChartView.descriptionText = ""
        //scatterChartView.setAutoScaleMinMaxEnabled(true)
        scatterChartView.data = scatterChartData
        

    }

    
}
