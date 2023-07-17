import { Component, OnInit } from '@angular/core';
import { DashboardService } from 'src/app/components/shared/services/dashboard.service';

@Component({
  selector: 'app-about-us',
  templateUrl: './about-us.component.html',
  styleUrls: ['./about-us.component.css']
})
export class AboutUsComponent implements OnInit {
  dataReclamation: any;
  dataType: any;
  dataMixed: any;
  chartOptions: any;
  constructor(private dashboardService: DashboardService){}
  ngOnInit(): void {
        this.getClaimsByStatus();
        this.getClaimsByMonth();
        this.getClaimsByType();
       
      }
  
      getClaimsByStatus(): void {
        this.dashboardService.getClaimsByStatus().subscribe(res => {
          this.dataReclamation = {
            labels: res.labels,
            datasets: [
              {
                data: res.values,
                backgroundColor: [
                  "#FFCE56",
                  "#36A2EB",
    
                  "#4dbd74",
                  "#607D8B",
                  "#D32F2F",
                  "#2f353a",
    
                ],
                hoverBackgroundColor: [
                  "#FFCE56",
                  "#36A2EB",
    
                  "#4dbd74",
                  "#607D8B",
                  "#D32F2F",
                  "#2f353a",
    
                ]
              }]
          };
        });
      }

      getClaimsByMonth():void {
        this.dashboardService.getClaimsByMonth().subscribe(res => {
          this.dataMixed = {
            labels: res.labels,
            datasets: [{
              type: 'line',
              label: 'Claims Resolved',
              borderColor: '#42A5F5',
              borderWidth: 2,
              fill: false,
              data: res.line1
            },
              {
              type: 'bar',
              label: 'Claims',
              backgroundColor: '#66BB6A',
              data: res.bar1,
              borderColor: 'white',
              borderWidth: 2
            }]
          };
          this.chartOptions = {
            responsive: true,
            title: {
              display: true,
              text: 'Réclamation/Support'
            },
            tooltips: {
              mode: 'index',
              intersect: true
            }
          };
    
        }, ex => console.log(ex));
      }



      getClaimsByType(): void {
        this.dashboardService.getClaimsByType().subscribe(res => {
          this.dataType = {
            labels: res.labels,
            datasets: [
              {
                data: res.values,
                backgroundColor: [
                  "#FFCE56",
                  "#36A2EB",
                  "#FF6384",
                  "#4dbd74",
                  "#607D8B",
                  "#D32F2F",
                  "#2f353a",
    
                ],
                hoverBackgroundColor: [
                  "#FFCE56",
                  "#36A2EB",
                  "#FF6384",
                  "#4dbd74",
                  "#607D8B",
                  "#D32F2F",
                  "#2f353a",
                ]
              }]
          };
        });
      }
    
    
    
  
   
  
  
  
    
    

}
