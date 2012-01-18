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
using InventoryWPFApplication.UserServiceReference;

namespace InventoryWPFApplication
{
    /// <summary>
    /// Interaction logic for InventoryAppWindow.xaml
    /// </summary>
    public partial class InventoryAppWindow : Window
    {
        private UserServiceClient proxy = null;

        public InventoryAppWindow()
        {
            InitializeComponent();
            proxy = new UserServiceClient();
            EnableDisableVisualControls(false);

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

        private void btnGetAllParts_Click(object sender, RoutedEventArgs e)
        {
            List<Inventory> results = proxy.getAllParts();
            listViewInventoryData.ItemsSource = results;
        }

        private void btnGetTotal_Click(object sender, RoutedEventArgs e)
        {
            MessageBox.Show(" " + proxy.calculateTotal(), "Total");
        }

        private void btnGetBalance_Click(object sender, RoutedEventArgs e)
        {
            MessageBox.Show(" " + proxy.calculateBalance(), "Balance");
        }

        private void btnGetReserved_Click(object sender, RoutedEventArgs e)
        {
            MessageBox.Show(" " + proxy.calculateReserved(), "Reserved");
        }

        private void btnSearch_Click(object sender, RoutedEventArgs e)
        {
            List<Inventory> results = proxy.searchPartByDescription(textBoxSearchByDescr.Text);
            listViewInventoryData.ItemsSource = results;
        }

        private void btnReserve_Click(object sender, RoutedEventArgs e)
        {
            int tmpInt;
            try
            {
                tmpInt = System.Convert.ToInt32(txtBoxResCount.Text);
            }
            catch (Exception)
            {
                tmpInt = 0;
            }

            proxy.reservePart(txtBoxIdRes.Text, tmpInt);
            
            List<Inventory> results = proxy.getAllParts();
            listViewInventoryData.ItemsSource = results;
        }

        private void btnCreateAdd_Click(object sender, RoutedEventArgs e)
        {
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

            proxy.addPart(txtBoxIDCreate.Text, txtBoxDescr.Text, tmpDbl, tmpInt);

            List<Inventory> results = proxy.getAllParts();
            listViewInventoryData.ItemsSource = results;
        }

        private void btnLogOut_Click(object sender, RoutedEventArgs e)
        {
            EnableDisableVisualControls(false);
        }

        private void btnLogIn_Click(object sender, RoutedEventArgs e)
        {
            bool tmpBool = proxy.logIn(txtBoxFN.Text, txtBoxLN.Text);
            if (tmpBool)
                EnableDisableVisualControls(true);
            else
            {
                EnableDisableVisualControls(false);
            }
        }
    }
}
