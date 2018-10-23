import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatDialogModule, MatIconModule, MatFormField, MatDialog, MatButtonModule, MatInputModule, MatListModule, MatSidenavModule, MatToolbarModule, MatCardModule, MatFormFieldModule } from '@angular/material';

@NgModule({
  imports: [
    CommonModule,
    MatButtonModule,
    MatDialog,
    MatDialogModule,
    MatIconModule,
    MatFormField,
    MatInputModule,
    MatListModule,
    MatSidenavModule,
    MatToolbarModule,
  ],
  exports: [
    MatButtonModule,
    MatCardModule,
    MatDialogModule,
    MatIconModule,
    MatFormFieldModule,
    MatInputModule,
    MatListModule,
    MatSidenavModule,
  ],

  declarations: [],

  providers: [
    MatDialog
  ]
})
export class MaterialModule { }
