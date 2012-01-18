using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;
using System.Data;
using InventoryIAssembly;
using InventoryAssembly;

namespace InventoryWindowsApplication
{
	/// <summary>
    /// Summary description for InventoryAppFrm.
	/// </summary>
	public class InventoryAppFrm : System.Windows.Forms.Form
	{
        private IUserSessInterface currUserSess = null;

		private System.Windows.Forms.Label label1;
		private System.Windows.Forms.TextBox txtBoxFN;
		private System.Windows.Forms.TextBox txtBoxLN;
		private System.Windows.Forms.Label label2;
		private System.Windows.Forms.Button btnLogIn;
		private System.Windows.Forms.Button btnLogOut;
		private System.Windows.Forms.GroupBox groupBox1;
		private System.Windows.Forms.GroupBox groupBox2;
		private System.Windows.Forms.Label label3;
		private System.Windows.Forms.Label label4;
		private System.Windows.Forms.Label label5;
		private System.Windows.Forms.Label label6;
		private System.Windows.Forms.Label label7;
		private System.Windows.Forms.Label label8;
		private System.Windows.Forms.Label label9;
		private System.Windows.Forms.TextBox txtBoxIDCreate;
		private System.Windows.Forms.TextBox txtBoxDescr;
		private System.Windows.Forms.TextBox txtBoxPrice;
		private System.Windows.Forms.TextBox txtBoxCount;
		private System.Windows.Forms.Button btnCreateAdd;
		private System.Windows.Forms.TextBox txtBoxIdRes;
		private System.Windows.Forms.TextBox txtBoxResCount;
		private System.Windows.Forms.Button btnReserve;
		private System.Windows.Forms.TextBox textBoxSearchByDescr;
		private System.Windows.Forms.Button btnSearch;
		private System.Windows.Forms.Button btnGetAllParts;
		private System.Windows.Forms.Button btnGetTotal;
		private System.Windows.Forms.Button btnGetBalance;
		private System.Windows.Forms.Button btnGetReserved;
        private ListView listViewInventoryData;
        private ColumnHeader ID;
        private ColumnHeader Description;
        private ColumnHeader Price;
        private ColumnHeader Count;
        private ColumnHeader Reserved;
		/// <summary>
		/// Required designer variable.
		/// </summary>
		private System.ComponentModel.Container components = null;

		public InventoryAppFrm()
		{
			//
			// Required for Windows Form Designer support
			//
			InitializeComponent();
            EnableDisableVisualControls(false);
			//
			// TODO: Add any constructor code after InitializeComponent call
			//
		}

		/// <summary>
		/// Clean up any resources being used.
		/// </summary>
		protected override void Dispose( bool disposing )
		{
			if( disposing )
			{
				if (components != null) 
				{
					components.Dispose();
				}
			}
			base.Dispose( disposing );
		}

		#region Windows Form Designer generated code
		/// <summary>
		/// Required method for Designer support - do not modify
		/// the contents of this method with the code editor.
		/// </summary>
		private void InitializeComponent()
		{
            this.label1 = new System.Windows.Forms.Label();
            this.txtBoxFN = new System.Windows.Forms.TextBox();
            this.txtBoxLN = new System.Windows.Forms.TextBox();
            this.label2 = new System.Windows.Forms.Label();
            this.btnLogIn = new System.Windows.Forms.Button();
            this.btnLogOut = new System.Windows.Forms.Button();
            this.groupBox1 = new System.Windows.Forms.GroupBox();
            this.groupBox2 = new System.Windows.Forms.GroupBox();
            this.btnSearch = new System.Windows.Forms.Button();
            this.textBoxSearchByDescr = new System.Windows.Forms.TextBox();
            this.btnReserve = new System.Windows.Forms.Button();
            this.txtBoxResCount = new System.Windows.Forms.TextBox();
            this.txtBoxIdRes = new System.Windows.Forms.TextBox();
            this.btnCreateAdd = new System.Windows.Forms.Button();
            this.txtBoxCount = new System.Windows.Forms.TextBox();
            this.txtBoxPrice = new System.Windows.Forms.TextBox();
            this.txtBoxDescr = new System.Windows.Forms.TextBox();
            this.label9 = new System.Windows.Forms.Label();
            this.label8 = new System.Windows.Forms.Label();
            this.label7 = new System.Windows.Forms.Label();
            this.label6 = new System.Windows.Forms.Label();
            this.label5 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.txtBoxIDCreate = new System.Windows.Forms.TextBox();
            this.btnGetAllParts = new System.Windows.Forms.Button();
            this.btnGetTotal = new System.Windows.Forms.Button();
            this.btnGetBalance = new System.Windows.Forms.Button();
            this.btnGetReserved = new System.Windows.Forms.Button();
            this.listViewInventoryData = new System.Windows.Forms.ListView();
            this.ID = new System.Windows.Forms.ColumnHeader();
            this.Description = new System.Windows.Forms.ColumnHeader();
            this.Price = new System.Windows.Forms.ColumnHeader();
            this.Count = new System.Windows.Forms.ColumnHeader();
            this.Reserved = new System.Windows.Forms.ColumnHeader();
            this.groupBox1.SuspendLayout();
            this.groupBox2.SuspendLayout();
            this.SuspendLayout();
            // 
            // label1
            // 
            this.label1.Location = new System.Drawing.Point(24, 24);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(83, 22);
            this.label1.TabIndex = 0;
            this.label1.Text = "First Name:";
            // 
            // txtBoxFN
            // 
            this.txtBoxFN.Location = new System.Drawing.Point(104, 24);
            this.txtBoxFN.Name = "txtBoxFN";
            this.txtBoxFN.Size = new System.Drawing.Size(288, 22);
            this.txtBoxFN.TabIndex = 1;
            // 
            // txtBoxLN
            // 
            this.txtBoxLN.Location = new System.Drawing.Point(104, 56);
            this.txtBoxLN.Name = "txtBoxLN";
            this.txtBoxLN.Size = new System.Drawing.Size(288, 22);
            this.txtBoxLN.TabIndex = 3;
            // 
            // label2
            // 
            this.label2.Location = new System.Drawing.Point(24, 56);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(83, 22);
            this.label2.TabIndex = 2;
            this.label2.Text = "Last Name:";
            // 
            // btnLogIn
            // 
            this.btnLogIn.Location = new System.Drawing.Point(400, 24);
            this.btnLogIn.Name = "btnLogIn";
            this.btnLogIn.Size = new System.Drawing.Size(96, 26);
            this.btnLogIn.TabIndex = 4;
            this.btnLogIn.Text = "LogIn";
            this.btnLogIn.Click += new System.EventHandler(this.btnLogIn_Click);
            // 
            // btnLogOut
            // 
            this.btnLogOut.Location = new System.Drawing.Point(400, 56);
            this.btnLogOut.Name = "btnLogOut";
            this.btnLogOut.Size = new System.Drawing.Size(96, 26);
            this.btnLogOut.TabIndex = 5;
            this.btnLogOut.Text = "LogOut";
            this.btnLogOut.Click += new System.EventHandler(this.btnLogOut_Click);
            // 
            // groupBox1
            // 
            this.groupBox1.Controls.Add(this.txtBoxFN);
            this.groupBox1.Controls.Add(this.btnLogIn);
            this.groupBox1.Controls.Add(this.label1);
            this.groupBox1.Controls.Add(this.txtBoxLN);
            this.groupBox1.Controls.Add(this.btnLogOut);
            this.groupBox1.Controls.Add(this.label2);
            this.groupBox1.Location = new System.Drawing.Point(8, 8);
            this.groupBox1.Name = "groupBox1";
            this.groupBox1.Size = new System.Drawing.Size(528, 112);
            this.groupBox1.TabIndex = 6;
            this.groupBox1.TabStop = false;
            // 
            // groupBox2
            // 
            this.groupBox2.Controls.Add(this.btnSearch);
            this.groupBox2.Controls.Add(this.textBoxSearchByDescr);
            this.groupBox2.Controls.Add(this.btnReserve);
            this.groupBox2.Controls.Add(this.txtBoxResCount);
            this.groupBox2.Controls.Add(this.txtBoxIdRes);
            this.groupBox2.Controls.Add(this.btnCreateAdd);
            this.groupBox2.Controls.Add(this.txtBoxCount);
            this.groupBox2.Controls.Add(this.txtBoxPrice);
            this.groupBox2.Controls.Add(this.txtBoxDescr);
            this.groupBox2.Controls.Add(this.label9);
            this.groupBox2.Controls.Add(this.label8);
            this.groupBox2.Controls.Add(this.label7);
            this.groupBox2.Controls.Add(this.label6);
            this.groupBox2.Controls.Add(this.label5);
            this.groupBox2.Controls.Add(this.label4);
            this.groupBox2.Controls.Add(this.label3);
            this.groupBox2.Controls.Add(this.txtBoxIDCreate);
            this.groupBox2.Location = new System.Drawing.Point(8, 128);
            this.groupBox2.Name = "groupBox2";
            this.groupBox2.Size = new System.Drawing.Size(528, 176);
            this.groupBox2.TabIndex = 7;
            this.groupBox2.TabStop = false;
            // 
            // btnSearch
            // 
            this.btnSearch.Location = new System.Drawing.Point(400, 136);
            this.btnSearch.Name = "btnSearch";
            this.btnSearch.Size = new System.Drawing.Size(96, 26);
            this.btnSearch.TabIndex = 21;
            this.btnSearch.Text = "Search";
            this.btnSearch.Click += new System.EventHandler(this.btnSearch_Click);
            // 
            // textBoxSearchByDescr
            // 
            this.textBoxSearchByDescr.Location = new System.Drawing.Point(167, 136);
            this.textBoxSearchByDescr.Name = "textBoxSearchByDescr";
            this.textBoxSearchByDescr.Size = new System.Drawing.Size(225, 22);
            this.textBoxSearchByDescr.TabIndex = 20;
            // 
            // btnReserve
            // 
            this.btnReserve.Location = new System.Drawing.Point(400, 96);
            this.btnReserve.Name = "btnReserve";
            this.btnReserve.Size = new System.Drawing.Size(96, 26);
            this.btnReserve.TabIndex = 19;
            this.btnReserve.Text = "Reserve";
            this.btnReserve.Click += new System.EventHandler(this.btnReserve_Click);
            // 
            // txtBoxResCount
            // 
            this.txtBoxResCount.Location = new System.Drawing.Point(304, 96);
            this.txtBoxResCount.Name = "txtBoxResCount";
            this.txtBoxResCount.Size = new System.Drawing.Size(88, 22);
            this.txtBoxResCount.TabIndex = 18;
            // 
            // txtBoxIdRes
            // 
            this.txtBoxIdRes.Location = new System.Drawing.Point(232, 96);
            this.txtBoxIdRes.Name = "txtBoxIdRes";
            this.txtBoxIdRes.Size = new System.Drawing.Size(64, 22);
            this.txtBoxIdRes.TabIndex = 17;
            // 
            // btnCreateAdd
            // 
            this.btnCreateAdd.Location = new System.Drawing.Point(400, 40);
            this.btnCreateAdd.Name = "btnCreateAdd";
            this.btnCreateAdd.Size = new System.Drawing.Size(96, 26);
            this.btnCreateAdd.TabIndex = 16;
            this.btnCreateAdd.Text = "Create/AddQ";
            this.btnCreateAdd.Click += new System.EventHandler(this.btnCreateAdd_Click);
            // 
            // txtBoxCount
            // 
            this.txtBoxCount.Location = new System.Drawing.Point(336, 40);
            this.txtBoxCount.Name = "txtBoxCount";
            this.txtBoxCount.Size = new System.Drawing.Size(56, 22);
            this.txtBoxCount.TabIndex = 15;
            // 
            // txtBoxPrice
            // 
            this.txtBoxPrice.Location = new System.Drawing.Point(280, 40);
            this.txtBoxPrice.Name = "txtBoxPrice";
            this.txtBoxPrice.Size = new System.Drawing.Size(48, 22);
            this.txtBoxPrice.TabIndex = 14;
            // 
            // txtBoxDescr
            // 
            this.txtBoxDescr.Location = new System.Drawing.Point(88, 40);
            this.txtBoxDescr.Name = "txtBoxDescr";
            this.txtBoxDescr.Size = new System.Drawing.Size(184, 22);
            this.txtBoxDescr.TabIndex = 13;
            // 
            // label9
            // 
            this.label9.Location = new System.Drawing.Point(16, 136);
            this.label9.Name = "label9";
            this.label9.Size = new System.Drawing.Size(159, 16);
            this.label9.TabIndex = 12;
            this.label9.Text = "Search Part by Descr.:";
            // 
            // label8
            // 
            this.label8.Location = new System.Drawing.Point(304, 80);
            this.label8.Name = "label8";
            this.label8.Size = new System.Drawing.Size(72, 16);
            this.label8.TabIndex = 11;
            this.label8.Text = "Res. Count";
            // 
            // label7
            // 
            this.label7.Location = new System.Drawing.Point(232, 80);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(24, 16);
            this.label7.TabIndex = 10;
            this.label7.Text = "ID";
            // 
            // label6
            // 
            this.label6.Location = new System.Drawing.Point(336, 24);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(56, 16);
            this.label6.TabIndex = 9;
            this.label6.Text = "Count";
            // 
            // label5
            // 
            this.label5.Location = new System.Drawing.Point(280, 24);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(48, 16);
            this.label5.TabIndex = 8;
            this.label5.Text = "Price";
            // 
            // label4
            // 
            this.label4.Location = new System.Drawing.Point(88, 24);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(87, 16);
            this.label4.TabIndex = 7;
            this.label4.Text = "Description";
            // 
            // label3
            // 
            this.label3.Location = new System.Drawing.Point(16, 24);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(24, 16);
            this.label3.TabIndex = 6;
            this.label3.Text = "ID";
            // 
            // txtBoxIDCreate
            // 
            this.txtBoxIDCreate.Location = new System.Drawing.Point(16, 40);
            this.txtBoxIDCreate.Name = "txtBoxIDCreate";
            this.txtBoxIDCreate.Size = new System.Drawing.Size(64, 22);
            this.txtBoxIDCreate.TabIndex = 6;
            // 
            // btnGetAllParts
            // 
            this.btnGetAllParts.Location = new System.Drawing.Point(50, 459);
            this.btnGetAllParts.Name = "btnGetAllParts";
            this.btnGetAllParts.Size = new System.Drawing.Size(96, 26);
            this.btnGetAllParts.TabIndex = 22;
            this.btnGetAllParts.Text = "GetAllParts";
            this.btnGetAllParts.Click += new System.EventHandler(this.btnGetAllParts_Click);
            // 
            // btnGetTotal
            // 
            this.btnGetTotal.Location = new System.Drawing.Point(162, 459);
            this.btnGetTotal.Name = "btnGetTotal";
            this.btnGetTotal.Size = new System.Drawing.Size(96, 26);
            this.btnGetTotal.TabIndex = 23;
            this.btnGetTotal.Text = "GetTotal";
            this.btnGetTotal.Click += new System.EventHandler(this.btnGetTotal_Click);
            // 
            // btnGetBalance
            // 
            this.btnGetBalance.Location = new System.Drawing.Point(274, 459);
            this.btnGetBalance.Name = "btnGetBalance";
            this.btnGetBalance.Size = new System.Drawing.Size(96, 26);
            this.btnGetBalance.TabIndex = 24;
            this.btnGetBalance.Text = "GetBalance";
            this.btnGetBalance.Click += new System.EventHandler(this.btnGetBalance_Click);
            // 
            // btnGetReserved
            // 
            this.btnGetReserved.Location = new System.Drawing.Point(386, 459);
            this.btnGetReserved.Name = "btnGetReserved";
            this.btnGetReserved.Size = new System.Drawing.Size(100, 26);
            this.btnGetReserved.TabIndex = 25;
            this.btnGetReserved.Text = "GetReserved";
            this.btnGetReserved.Click += new System.EventHandler(this.btnGetReserved_Click);
            // 
            // listViewInventoryData
            // 
            this.listViewInventoryData.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.ID,
            this.Description,
            this.Price,
            this.Count,
            this.Reserved});
            this.listViewInventoryData.GridLines = true;
            this.listViewInventoryData.Location = new System.Drawing.Point(8, 320);
            this.listViewInventoryData.Name = "listViewInventoryData";
            this.listViewInventoryData.Size = new System.Drawing.Size(528, 122);
            this.listViewInventoryData.TabIndex = 22;
            this.listViewInventoryData.UseCompatibleStateImageBehavior = false;
            this.listViewInventoryData.View = System.Windows.Forms.View.Details;
            // 
            // ID
            // 
            this.ID.Text = "ID";
            this.ID.Width = 119;
            // 
            // Description
            // 
            this.Description.Text = "Description";
            this.Description.Width = 209;
            // 
            // Price
            // 
            this.Price.Text = "Price";
            this.Price.Width = 58;
            // 
            // Count
            // 
            this.Count.Text = "Count";
            this.Count.Width = 63;
            // 
            // Reserved
            // 
            this.Reserved.Text = "Reserved";
            this.Reserved.Width = 72;
            // 
            // InventoryAppFrm
            // 
            this.AutoScaleBaseSize = new System.Drawing.Size(6, 15);
            this.ClientSize = new System.Drawing.Size(556, 497);
            this.Controls.Add(this.listViewInventoryData);
            this.Controls.Add(this.btnGetReserved);
            this.Controls.Add(this.btnGetBalance);
            this.Controls.Add(this.btnGetTotal);
            this.Controls.Add(this.groupBox2);
            this.Controls.Add(this.groupBox1);
            this.Controls.Add(this.btnGetAllParts);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.Fixed3D;
            this.Name = "InventoryAppFrm";
            this.Text = "Inventory Application";
            this.groupBox1.ResumeLayout(false);
            this.groupBox1.PerformLayout();
            this.groupBox2.ResumeLayout(false);
            this.groupBox2.PerformLayout();
            this.ResumeLayout(false);

		}
		#endregion

        private void EnableDisableVisualControls(bool mode)
        {
            btnLogIn.Enabled = !mode;
            btnLogOut.Enabled = mode;
            btnSearch.Enabled = mode;
            textBoxSearchByDescr.Enabled = mode;
            btnReserve.Enabled = mode;
            txtBoxResCount.Enabled = mode;
            txtBoxIdRes.Enabled = mode;
            btnCreateAdd.Enabled = mode;
            txtBoxCount.Enabled = mode;
            txtBoxPrice.Enabled = mode;
            txtBoxDescr.Enabled = mode;
            txtBoxIDCreate.Enabled = mode;
            btnGetAllParts.Enabled = mode;
            btnGetTotal.Enabled = mode;
            btnGetBalance.Enabled = mode;
            btnGetReserved.Enabled = mode;
            listViewInventoryData.Enabled = mode;
        }

		private void btnLogIn_Click(object sender, System.EventArgs e)
		{
            currUserSess = new UserSess();
            bool tmpBool = currUserSess.logIn(txtBoxFN.Text, txtBoxLN.Text);
            if (tmpBool)
                EnableDisableVisualControls(true);
            else
            {
                EnableDisableVisualControls(false);
                currUserSess = null;
            }
		}

		private void btnLogOut_Click(object sender, System.EventArgs e)
		{
            currUserSess = null;
            EnableDisableVisualControls(false);
		}

		private void btnGetAllParts_Click(object sender, System.EventArgs e)
		{
            if (currUserSess == null) return;

            listViewInventoryData.Items.Clear();
            InventoryDTO[] tmpArrDTO = currUserSess.getAllParts();
            if (tmpArrDTO != null)
            {
                for (int i = 0; i < tmpArrDTO.Length; i++)
                {
                    InventoryDTO tmpPart = tmpArrDTO[i];
                    String[] myItems = new String[] { tmpPart.getId(),
                                                      tmpPart.getDescription(),
                                                      System.Convert.ToString(tmpPart.getPrice()),
                                                      System.Convert.ToString(tmpPart.getInstock()),
                                                      System.Convert.ToString(tmpPart.getReserved()) };
                    ListViewItem tmpItem = new ListViewItem(myItems);
                    listViewInventoryData.Items.Add(tmpItem);
                }
            }
            else
			    MessageBox.Show("error");
		}

        private void btnGetTotal_Click(object sender, EventArgs e)
        {
            if (currUserSess == null) return;
            MessageBox.Show(" " + currUserSess.calculateTotal(), "Total");
        }

        private void btnGetBalance_Click(object sender, EventArgs e)
        {
            if (currUserSess == null) return;
            MessageBox.Show(" " + currUserSess.calculateBalance(), "Balance");
        }

        private void btnGetReserved_Click(object sender, EventArgs e)
        {
            if (currUserSess == null) return;
            MessageBox.Show(" " + currUserSess.calculateReserved(), "Reserved");
        }

        private void btnCreateAdd_Click(object sender, EventArgs e)
        {
            if (currUserSess == null) return;

            int tmpInt;
            double tmpDbl;
            
            try {
                tmpDbl = System.Convert.ToDouble(txtBoxPrice.Text);
            }
            catch (Exception) {
                tmpDbl = 0;
            }
            try {
                tmpInt = System.Convert.ToInt32(txtBoxCount.Text);
            }
            catch (Exception) {
                tmpInt = 0;
            }

            currUserSess.addPart(txtBoxIDCreate.Text, txtBoxDescr.Text, tmpDbl, tmpInt);
        }
        
        private void btnReserve_Click(object sender, EventArgs e)
        {
            if (currUserSess == null) return;
            int tmpInt;
            try {
                tmpInt = System.Convert.ToInt32(txtBoxResCount.Text);
            }
            catch (Exception) {
                tmpInt = 0;
            }

            currUserSess.reservePart(txtBoxIdRes.Text, tmpInt);
        }

        private void btnSearch_Click(object sender, EventArgs e)
        {
            if (currUserSess == null) return;

            listViewInventoryData.Items.Clear();
            InventoryDTO[] tmpArrDTO = currUserSess.searchPartByDescription(textBoxSearchByDescr.Text);
            if (tmpArrDTO != null)
            {
                for (int i = 0; i < tmpArrDTO.Length; i++)
                {
                    InventoryDTO tmpPart = tmpArrDTO[i];
                    String[] myItems = new String[] { tmpPart.getId(),
                                                      tmpPart.getDescription(),
                                                      System.Convert.ToString(tmpPart.getPrice()),
                                                      System.Convert.ToString(tmpPart.getInstock()),
                                                      System.Convert.ToString(tmpPart.getReserved()) };
                    ListViewItem tmpItem = new ListViewItem(myItems);
                    listViewInventoryData.Items.Add(tmpItem);
                }
            }
            else
                MessageBox.Show("error");

        }

	}
}
