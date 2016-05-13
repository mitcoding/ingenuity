//
//  DataManager.swift
//  iOSChartsDemo
//
//  Created by guest on 5/10/16.
//  Copyright © 2016 Appcoda. All rights reserved.
//

import Foundation
import SwiftyJSON

class DataManager {

  //  let json = JSON(data: dataFromNetworking)
  //  let json = JSON(jsonObject)
    
    let salesUrl = NSURL(string: "http://localhost:8080/api/sales")
    //let viewsUrl = NSURL(string: "http://ingenuity-1307.appspot.com/ingenuity/api/get/views")
    
    
    let session: NSURLSession = {
        let config = NSURLSessionConfiguration.defaultSessionConfiguration()
        return NSURLSession(configuration: config)
    }()
    
    func getSalesData(){
        //let url = salesUrl
        let request = NSURLRequest(URL: salesUrl!)
        let task = session.dataTaskWithRequest(request) {
            (data, response, error) -> Void in
            if let jsonData = data {
                if let jsonString = String(data: jsonData,
                                           encoding: NSUTF8StringEncoding) {
                    print(jsonString)
                } }
            else if let requestError = error {
                print("Error fetching interesting photos: \(requestError)")
            }
            else {
                print("Unexpected error with the request")
            }
        }
        task.resume()
        
    }
        
//        var request = NSURLRequest(URL: url!)
//        var data = NSURLConnection.sendSynchronousRequest(request, returningResponse: nil, error: nil)
//        if data != nil {
//            var hoge = JSON(data: data!)
//            println(hoge)
//        }
//        
//        if let dataFromString = jsonString.dataUsingEncoding(NSUTF8StringEncoding, allowLossyConversion: false) {
//            let json = JSON(data: dataFromString)
//        }
//
//        func getSitecatalystData()
//    {
        
        
        
//        var baseURLString = "https://api.flickr.com/services/rest"
//        
//            guard let
//                photoID = json["id"] as? String,
//                title = json["title"] as? String,
//                dateString = json["datetaken"] as? String,
//                photoURLString = json["url_h"] as? String,
//                url = NSURL(string: photoURLString),
//                dateTaken = dateFormatter.dateFromString(dateString) else {
//                    // Don't have enough information to construct a Photo
//                    return nil }
//            let fetchRequest = NSFetchRequest(entityName: "Photo")
//            let predicate = NSPredicate(format: "photoID == \(photoID)")
//            fetchRequest.predicate = predicate
//            var fetchedPhotos: [Photo]!
//            context.performBlockAndWait() {
//                fetchedPhotos = try! context.executeFetchRequest(fetchRequest) as! [Photo]
//            }
        
        
        
        
        
        
        
        
        
}
