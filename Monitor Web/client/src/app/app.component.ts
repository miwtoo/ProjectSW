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




  monitor:any = {
    type:String,
    reso:String
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
  }

  toggle2() {
    this.select2 = !this.select2;

    // CHANGE THE NAME OF THE BUTTON.
    if(this.select2)  
      this.buttonName2 = "ไม่เลือก";
    else
      this.buttonName2 = "เลือก";
  }

  toggle3() {
    this.select3 = !this.select3;

    // CHANGE THE NAME OF THE BUTTON.
    if(this.select3)  
      this.buttonName3 = "ไม่เลือก";
    else
      this.buttonName3 = "เลือก";
  }

  toggle4() {
    this.select4 = !this.select4;

    // CHANGE THE NAME OF THE BUTTON.
    if(this.select4)  
      this.buttonName4 = "ไม่เลือก";
    else
      this.buttonName4 = "เลือก";
  }

  toggle5() {
    this.select5 = !this.select5;

    // CHANGE THE NAME OF THE BUTTON.
    if(this.select5)  
      this.buttonName5 = "ไม่เลือก";
    else
      this.buttonName5 = "เลือก";
  }

  toggle6() {
    this.select6 = !this.select6;

    // CHANGE THE NAME OF THE BUTTON.
    if(this.select6)  
      this.buttonName6 = "ไม่เลือก";
    else
      this.buttonName6 = "เลือก";
  }

  toggle7() {
    this.select7 = !this.select7;

    // CHANGE THE NAME OF THE BUTTON.
    if(this.select7)  
      this.buttonName7 = "ไม่เลือก";
    else
      this.buttonName7 = "เลือก";
  }

  toggle8() {
    this.select8 = !this.select8;

    // CHANGE THE NAME OF THE BUTTON.
    if(this.select8)  
      this.buttonName8 = "ไม่เลือก";
    else
      this.buttonName8 = "เลือก";
  }

  toggle9() {
    this.select9 = !this.select9;

    // CHANGE THE NAME OF THE BUTTON.
    if(this.select9)  
      this.buttonName9 = "ไม่เลือก";
    else
      this.buttonName9 = "เลือก";
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
