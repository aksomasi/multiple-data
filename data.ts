import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{
  public attestationToView: any[] = [];

  persons = [
    {comment: "Malcom", attachment: "Reynolds.pdf", createdBy: "Malcom"},
    {comment: "Malcom", attachment: "Frye.jpeg", createdBy: "Malcom"},
    {comment: "Malcom", attachment: "Frye.csv", createdBy: "Abc"},
    {comment: "Jayne", attachment: "Cobb.pdf", createdBy: "Malcom"}
  ];


  constructor() {

  }

  ngOnInit(): void {
    const uniqueValues: any = this.unique(this.persons, ['comment', 'createdBy']);
    uniqueValues.forEach((val: any) => {
      this.fetch(val.comment, val.createdBy);
    });
    console.log(this.attestationToView);
  }

  fetch(comment: any, createdBy: any) {
    const filt = this.persons.filter((val: any) => val.comment === comment && val.createdBy === createdBy);
    const attachments = filt.map((val: any) => val.attachment);
    const attestation = {comment: comment, attachment: attachments.join(" , "), createdBy: createdBy};
       this.attestationToView.push(attestation);
  }


  unique(arr: any[], keyProps: any[]) {
    const kvArray: any = arr.map(entry => {
      const key = keyProps.map(k => entry[k]).join('|');
      return [key, entry];
    });
    const map = new Map(kvArray);
    return Array.from(map.values());
  }
}
