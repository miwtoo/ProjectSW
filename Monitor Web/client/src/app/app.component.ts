import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  typeList: Array<string>;


  monitor:any = {
    type:String
  };

  

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
    
  }



}
