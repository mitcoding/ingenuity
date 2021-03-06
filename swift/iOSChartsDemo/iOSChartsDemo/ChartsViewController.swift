//
//  ChartsViewController.swift
//  iOSChartsDemo
//
//  Created by Joyce Echessa on 6/12/15.
//  Copyright (c) 2015 Appcoda. All rights reserved.
//

import UIKit
import Charts
import SwiftyJSON

class ChartsViewController: UIViewController {
    
    @IBOutlet weak var lineChartView: LineChartView!
    @IBOutlet weak var pieChartView: PieChartView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        // Do any additional setup after loading the view.
        
        let months = ["Mon", "Tue", "Wed", "Thu", "Fri"]
        var unitsSold = [5.6, 5.6, 4.6, 4.0, 3.6, 16.0]
        //var unitSold = [Double]()
        
        var dataFromString = "[{'Fund':'AMBAL','Date':'1/4/2016','529A':1501629,'529C':540188,'529E':34800,'A':25219504,'C':8503050,'F1':1713683,'F2':11014412,'R1':60455,'R2':1332697,'R2E':180909,'R3':2562303,'R4':2752621,'R5':804128,'R6':698716,'DailyTotal':56919095},{'Fund':'AMBAL','Date':'1/5/2016','529A':680796,'529C':123056,'529E':null,'A':16689362,'C':8035557,'F1':906832,'F2':7243176,'R1':59220,'R2':642437,'R2E':1292977,'R3':1824519,'R4':7679133,'R5':3665855,'R6':7251722,'DailyTotal':56094642},{'Fund':'AMBAL','Date':'1/6/2016','529A':510346,'529C':130578,'529E':null,'A':19462867,'C':6508150,'F1':1362309,'F2':8738231,'R1':65200,'R2':600035,'R2E':618274,'R3':2692784,'R4':334595,'R5':449466,'R6':5221063,'DailyTotal':46693898},{'Fund':'AMBAL','Date':'1/7/2016','529A':459744,'529C':253274,'529E':null,'A':23504343,'C':7918401,'F1':1546778,'F2':4218829,'R1':39000,'R2':306240,'R2E':41949,'R3':555498,'R4':818781,'R5':204416,'R6':308741,'DailyTotal':40175994},{'Fund':'AMBAL','Date':'1/8/2016','529A':455955,'529C':486501,'529E':45000,'A':20982065,'C':6240096,'F1':1029248,'F2':4447598,'R1':79231,'R2':736360,'R2E':null,'R3':1445322,'R4':453507,'R5':51074,'R6':98178,'DailyTotal':36550135},{'Fund':'AMBAL','Date':'Share Class Total','529A':3608470,'529C':1533597,'529E':79800,'A':105858141,'C':37205254,'F1':6558850,'F2':35662246,'R1':303106,'R2':3617769,'R2E':2134109,'R3':9080426,'R4':12038637,'R5':5174939,'R6':13578420,'DailyTotal':236433764}]"
        
        let json = JSON(dataFromString)
        //If json is .Array
        //The `index` is 0..<json.count's string value
        for (index,subJson):(String, JSON) in json {
            //Do something you want
           // unitsSold.
        }
        setChart(months, values: unitsSold)
        
    }
    
    func setChart(dataPoints: [String], values: [Double]) {
        
        var dataEntries: [ChartDataEntry] = []
        
        for i in 0..<dataPoints.count {
            let dataEntry = ChartDataEntry(value: values[i], xIndex: i)
            dataEntries.append(dataEntry)
        }
        
//        let pieChartDataSet = PieChartDataSet(yVals: dataEntries, label: "Sold")
//        let pieChartData = PieChartData(xVals: dataPoints, dataSet: pieChartDataSet)
//        pieChartView.data = pieChartData
//        
//        var colors: [UIColor] = []
//        
//        for i in 0..<dataPoints.count {
//            let red = Double(arc4random_uniform(256))
//            let green = Double(arc4random_uniform(256))
//            let blue = Double(arc4random_uniform(256))
//            
//            let color = UIColor(red: CGFloat(red/255), green: CGFloat(green/255), blue: CGFloat(blue/255), alpha: 1)
//            colors.append(color)
//        }
//        
//        pieChartDataSet.colors = colors
//        
//        
        let lineChartDataSet = LineChartDataSet(yVals: dataEntries, label: "AMBAL")
        let lineChartData = LineChartData(xVals: dataPoints, dataSet: lineChartDataSet)
        lineChartView.data = lineChartData
        
        //pieChartView.descriptionText = ""
        
        lineChartView.descriptionText = "Sales in Millions"
        
    }
    
}
