using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using InventoryIAssembly;
using InventoryAssembly;
using System.Collections.ObjectModel;
using System.Data;

namespace InventoryWPFApplication
{
    /// <summary>
    /// Interaction logic for InventoryAppWindow.xaml
    /// </summary>
    /// 
    public partial class InventoryAppWindow : Window
    {
        private IUserSessInterface currUserSess = null;
        //http://social.msdn.microsoft.com/Forums/en-US/wpf/thread/c8c39d0f-7880-481a-ae9a-d230108407ac/
        //How to: Manage Local Data Files in Your Project
        DataTable listViewDataProvider = new DataTable();

        public InventoryAppWindow()
        {
            InitializeComponent();
            EnableDisableVisualControls(false);
            
            listViewDataProvider.Columns.Add("ID");
            listViewDataProvider.Columns.Add("Description");
            listViewDataProvider.Columns.Add("Price");
            listViewDataProvider.Columns.Add("Count");
            listViewDataProvider.Columns.Add("Reserved");
            Binding bind = new Binding();
            bind.Source = listViewDataProvider;
            listViewInventoryData.SetBinding(ListView.ItemsSourceProperty, bind);
        }


        private void EnableDisableVisualControls(bool mode)
        {
            btnLogIn.IsEnabled = !mode;
            btnLogOut.IsEnabled = mode;
            btnSearch.IsEnabled = mode;
            textBoxSearchByDescr.IsEnabled = mode;
            btnReserve.IsEnabled = mode;
            txtBoxResCount.IsEnabled = mode;
            txtBoxIdRes.IsEnabled = mode;
            btnCreateAdd.IsEnabled = mode;
            txtBoxCount.IsEnabled = mode;
            txtBoxPrice.IsEnabled = mode;
            txtBoxDescr.IsEnabled = mode;
            txtBoxIDCreate.IsEnabled = mode;
            btnGetAllParts.IsEnabled = mode;
            btnGetTotal.IsEnabled = mode;
            btnGetBalance.IsEnabled = mode;
            btnGetReserved.IsEnabled = mode;
            listViewInventoryData.IsEnabled = mode;
        }

        private void btnLogIn_Click(object sender, RoutedEventArgs e)
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

        private void btnLogOut_Click(object sender, RoutedEventArgs e)
        {
            currUserSess = null;
            EnableDisableVisualControls(false);
        }

        private void btnCreateAdd_Click(object sender, RoutedEventArgs e)
        {
            if (currUserSess == null) return;

            int tmpInt;
            double tmpDbl;

            try
            {
                tmpDbl = System.Convert.ToDouble(txtBoxPrice.Text);
            }
            catch (Exception)
            {
                tmpDbl = 0;
            }
            try
            {
                tmpInt = System.Convert.ToInt32(txtBoxCount.Text);
            }
            catch (Exception)
            {
                tmpInt = 0;
            }

            currUserSess.addPart(txtBoxIDCreate.Text, txtBoxDescr.Text, tmpDbl, tmpInt);
        }

        private void btnReserve_Click(object sender, RoutedEventArgs e)
        {
            if (currUserSess == null) return;
            int tmpInt;
            try
            {
                tmpInt = System.Convert.ToInt32(txtBoxResCount.Text);
            }
            catch (Exception)
            {
                tmpInt = 0;
            }

            currUserSess.reservePart(txtBoxIdRes.Text, tmpInt);
        }

        private void btnSearch_Click(object sender, RoutedEventArgs e)
        {
            if (currUserSess == null) return;

            listViewDataProvider.Clear();
            InventoryDTO[] tmpArrDTO = currUserSess.searchPartByDescription(textBoxSearchByDescr.Text);
            if (tmpArrDTO != null)
            {
                for (int i = 0; i < tmpArrDTO.Length; i++)
                {

                    InventoryDTO tmpPart = tmpArrDTO[i];

                    DataRow dr;
                    dr = listViewDataProvider.NewRow();
                    dr[0] = tmpPart.getId();
                    dr[1] = tmpPart.getDescription();
                    dr[2] = System.Convert.ToString(tmpPart.getPrice());
                    dr[3] = System.Convert.ToString(tmpPart.getInstock());
                    dr[4] = System.Convert.ToString(tmpPart.getReserved());
                    listViewDataProvider.Rows.Add(dr);
                }
            }
            else
                MessageBox.Show("error");
        }

    
        private void btnGetAllParts_Click(object sender, RoutedEventArgs e)
        {
            if (currUserSess == null) return;

            listViewDataProvider.Clear();

            InventoryDTO[] tmpArrDTO = currUserSess.getAllParts();
            if (tmpArrDTO != null)
            {
                for (int i = 0; i < tmpArrDTO.Length; i++)
                {
                    
                    InventoryDTO tmpPart = tmpArrDTO[i];

                    DataRow dr;
                    dr = listViewDataProvider.NewRow();
                    dr[0] = tmpPart.getId();
                    dr[1] = tmpPart.getDescription();
                    dr[2] = System.Convert.ToString(tmpPart.getPrice());
                    dr[3] = System.Convert.ToString(tmpPart.getInstock());
                    dr[4] = System.Convert.ToString(tmpPart.getReserved());
                    listViewDataProvider.Rows.Add(dr);
                }
            }
            else
                MessageBox.Show("error");
        }

        private void btnGetTotal_Click(object sender, RoutedEventArgs e)
        {
            if (currUserSess == null) return;
            MessageBox.Show(" " + currUserSess.calculateTotal(), "Total");
        }

        private void btnGetBalance_Click(object sender, RoutedEventArgs e)
        {
            if (currUserSess == null) return;
            MessageBox.Show(" " + currUserSess.calculateBalance(), "Balance");
        }

        private void btnGetReserved_Click(object sender, RoutedEventArgs e)
        {
            if (currUserSess == null) return;
            MessageBox.Show(" " + currUserSess.calculateReserved(), "Reserved");
        }
    }
}
