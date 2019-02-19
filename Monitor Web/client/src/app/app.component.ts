import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  typeList: Array<string>;
  resoList: Array<string>;
  panalList: Array<string>;
  aspectList: Array<string>;
  portList: Array<string>;
  refreshList: Array<string>;
  resList: Array<string>;
  sizeList: Array<string>;
  featureList: Array<string>;
  colorList: Array<string>;
  priceList: Array<string>;





  monitor:any = {
    type:String,
    reso:String,
    panal:String,
    aspect:String,
    port:String,
    refresh:String,
    res:String,
    size:String,
    feature:String,
    color:String,
    price:String,
  };

  result:any

  constructor(private http: HttpClient) { }

  ngOnInit() {

    this.http.get("http://localhost:8080/api/type").subscribe(
      (data: any) => {
        console.log("GET Request is successful ", data);
        this.typeList = data.data;
        
        console.log(this.typeList);
        
      },
      error => {
        console.log("Error", error);
        alert("ผิดพลาด " + error)
      }

    );
  }

  

  find(){
    this.http.get("http://localhost:8080/api/" + this.monitor.type).subscribe(
      (data:any) => {
        console.log("GET Request is successful ", data);
        this.result = data.data;
        
        console.log(this.result);
        
      },
      error => {
        console.log("Error", error);
        alert("ผิดพลาด " + error)
      }

    );
  }


  public select:boolean = false;
  public buttonName:any = 'เลือก';

  public select1:boolean = false;
  public buttonName1:any = 'เลือก';

  public select2:boolean = false;
  public buttonName2:any = 'เลือก';
  
  public select3:boolean = false;
  public buttonName3:any = 'เลือก';

  public select4:boolean = false;
  public buttonName4:any = 'เลือก';

  public select5:boolean = false;
  public buttonName5:any = 'เลือก';

  public select6:boolean = false;
  public buttonName6:any = 'เลือก';

  public select7:boolean = false;
  public buttonName7:any = 'เลือก';

  public select8:boolean = false;
  public buttonName8:any = 'เลือก';

  public select9:boolean = false;
  public buttonName9:any = 'เลือก';

  public select10:boolean = false;
  public buttonName10:any = 'เลือก';

  



  resotoggle() {
    this.select = !this.select;

    // CHANGE THE NAME OF THE BUTTON.
    if(this.select)  
      this.buttonName = "ไม่เลือก";
    else
      this.buttonName = "เลือก";

      this.http.get("http://localhost:8080/api/reso/"+this.monitor.type).subscribe(
      (data: any) => {
        console.log("GET Request is successful ", data);
        this.resoList = data.data;
        
        console.log(this.resoList);
        
      },
      error => {
        console.log("Error", error);
        alert("ผิดพลาด " + error)
      }

    );
  }

  toggle1() {
    this.select1 = !this.select1;

    // CHANGE THE NAME OF THE BUTTON.
    if(this.select1)  
      this.buttonName1 = "ไม่เลือก";
    else
      this.buttonName1 = "เลือก";


      
      this.http.get("http://localhost:8080/api/panal/"+this.monitor.reso).subscribe(
      (data: any) => {
        console.log("GET Request is successful ", data);
        this.panalList = data.data;
        
        console.log(this.resoList);
        
      },
      error => {
        console.log("Error", error);
        alert("ผิดพลาด " + error)
      }

    );
  }

  toggle2() {
    this.select2 = !this.select2;

    // CHANGE THE NAME OF THE BUTTON.
    if(this.select2)  
      this.buttonName2 = "ไม่เลือก";
    else
      this.buttonName2 = "เลือก";


      this.http.get("http://localhost:8080/api/aspect/"+this.monitor.panal).subscribe(
        (data: any) => {
          console.log("GET Request is successful ", data);
          this.aspectList = data.data;
          
          console.log(this.resoList);
          
        },
        error => {
          console.log("Error", error);
          alert("ผิดพลาด " + error)
        }
  
      );
  }

  toggle3() {
    this.select3 = !this.select3;

    // CHANGE THE NAME OF THE BUTTON.
    if(this.select3)  
      this.buttonName3 = "ไม่เลือก";
    else
      this.buttonName3 = "เลือก";

      this.http.get("http://localhost:8080/api/port/"+this.monitor.aspect).subscribe(
        (data: any) => {
          console.log("GET Request is successful ", data);
          this.portList = data.data;
          
          console.log(this.resoList);
          
        },
        error => {
          console.log("Error", error);
          alert("ผิดพลาด " + error)
        }
  
      );
  }

  toggle4() {
    this.select4 = !this.select4;

    // CHANGE THE NAME OF THE BUTTON.
    if(this.select4)  
      this.buttonName4 = "ไม่เลือก";
    else
      this.buttonName4 = "เลือก";


      this.http.get("http://localhost:8080/api/refreshRate/"+this.monitor.port).subscribe(
        (data: any) => {
          console.log("GET Request is successful ", data);
          this.refreshList = data.data;
          
          console.log(this.resoList);
          
        },
        error => {
          console.log("Error", error);
          alert("ผิดพลาด " + error)
        }
  
      );
  }

  toggle5() {
    this.select5 = !this.select5;

    // CHANGE THE NAME OF THE BUTTON.
    if(this.select5)  
      this.buttonName5 = "ไม่เลือก";
    else
      this.buttonName5 = "เลือก";


      this.http.get("http://localhost:8080/api/respon/"+this.monitor.refresh).subscribe(
        (data: any) => {
          console.log("GET Request is successful ", data);
          this.resList = data.data;
          
          console.log(this.resoList);
          
        },
        error => {
          console.log("Error", error);
          alert("ผิดพลาด " + error)
        }
  
      );


  }

  toggle6() {
    this.select6 = !this.select6;

    // CHANGE THE NAME OF THE BUTTON.
    if(this.select6)  
      this.buttonName6 = "ไม่เลือก";
    else
      this.buttonName6 = "เลือก";


      this.http.get("http://localhost:8080/api/size/"+this.monitor.res).subscribe(
        (data: any) => {
          console.log("GET Request is successful ", data);
          this.sizeList = data.data;
          
          console.log(this.resoList);
          
        },
        error => {
          console.log("Error", error);
          alert("ผิดพลาด " + error)
        }
  
      );
  }

  toggle7() {
    this.select7 = !this.select7;

    // CHANGE THE NAME OF THE BUTTON.
    if(this.select7)  
      this.buttonName7 = "ไม่เลือก";
    else
      this.buttonName7 = "เลือก";


      this.http.get("http://localhost:8080/api/future/"+this.monitor.size).subscribe(
        (data: any) => {
          console.log("GET Request is successful ", data);
          this.featureList = data.data;
          
          console.log(this.resoList);
          
        },
        error => {
          console.log("Error", error);
          alert("ผิดพลาด " + error)
        }
  
      );
  }

  toggle8() {
    this.select8 = !this.select8;

    // CHANGE THE NAME OF THE BUTTON.
    if(this.select8)  
      this.buttonName8 = "ไม่เลือก";
    else
      this.buttonName8 = "เลือก";
      this.http.get("http://localhost:8080/api/color/"+this.monitor.feature).subscribe(
        (data: any) => {
          console.log("GET Request is successful ", data);
          this.colorList = data.data;
          
          console.log(this.resoList);
          
        },
        error => {
          console.log("Error", error);
          alert("ผิดพลาด " + error)
        }
  
      );

  }

  toggle9() {
    this.select9 = !this.select9;

    // CHANGE THE NAME OF THE BUTTON.
    if(this.select9)  
      this.buttonName9 = "ไม่เลือก";
    else
      this.buttonName9 = "เลือก";


      this.http.get("http://localhost:8080/api/price/"+this.monitor.color).subscribe(
        (data: any) => {
          console.log("GET Request is successful ", data);
          this.priceList = data.data;
          
          console.log(this.resoList);
          
        },
        error => {
          console.log("Error", error);
          alert("ผิดพลาด " + error)
        }
  
      );
  }

  toggle10() {
    this.select10 = !this.select10;

    // CHANGE THE NAME OF THE BUTTON.
    if(this.select10)  
      this.buttonName10 = "ไม่เลือก";
    else
      this.buttonName10 = "เลือก";
  }



}
