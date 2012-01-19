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
        private User currentUser;

        public InventoryAppWindow()
        {
            InitializeComponent();
            proxy = new UserServiceClient();
            EnableDisableVisualControls(false);

        }

        private void EnableDisableVisualControls(bool mode)
        {
            if (mode)
            {
                populateAllPartsListBox();
                populateShoppingCartListBox();
            }
            else
            {
                clearShoppingCart();
            }
            btnLogIn.IsEnabled = !mode;
            btnLogOut.IsEnabled = mode;
            shuttleToCart.IsEnabled = mode;
            removeFromCart.IsEnabled = mode;
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
            allItemsListView.IsEnabled = mode;
            shoppingCartListView.IsEnabled = mode;
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
            currentUser = proxy.logIn(txtBoxFN.Text, txtBoxLN.Text);
            if (currentUser != null)
            {
                EnableDisableVisualControls(true);
            }
            else
            {
                EnableDisableVisualControls(false);
            }
        }

        private void populateAllPartsListBox()
        {
            List<Inventory> results = proxy.getAllParts();
            allItemsListView.ItemsSource = results;
            
        }

        private void populateShoppingCartListBox()
        {
            List<OrderItem> shoppingCartList = proxy.getShoppingCart(currentUser.ID);
            shoppingCartListView.ItemsSource = shoppingCartList;
        }

        private void clearShoppingCart()
        {
            allItemsListView.ItemsSource = new List<Inventory>();
            shoppingCartListView.ItemsSource = new List<Inventory>();
        }

        private void shuttleToCart_Click(object sender, RoutedEventArgs e)
        {
            Inventory selected = (Inventory)allItemsListView.SelectedItem;
            Console.WriteLine(selected.DESCRIPTION);
            proxy.addOrderItem(selected, currentUser, 1);
            populateShoppingCartListBox();
            OrderItem orderItem = new OrderItem();
        }
    }
}
