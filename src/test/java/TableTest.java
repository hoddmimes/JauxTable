import com.hoddmimes.auxtable.Table;
import com.hoddmimes.auxtable.TableAttribute;
import com.hoddmimes.auxtable.TableModel;
import org.junit.Test;

import java.util.List;
import javax.swing.*;
import java.awt.*;

import java.util.ArrayList;

public class TableTest extends JFrame implements Table.TableCallbackInterface
{
    TableModel<TestObject> mTableModel;
    Table mTable;

    private void init() {
        this.setTitle("Table Test");


        mTableModel = new TableModel( TestObject.class );
        mTable = new Table( mTableModel, new Dimension( 500, 140), this );

        // Add a few test object
        for( int i = 0; i < 4; i++ ) {
            mTableModel.add( createTestObject(i));
        }

        JPanel tRootPanel = new JPanel( new BorderLayout());
        tRootPanel.add( mTable, BorderLayout.CENTER );
        this.setContentPane( tRootPanel );
    }

    TestObject createTestObject( int pIndex )
    {
        List<String> tChoiceList = new ArrayList();
        for( int i = 0; i < 5; i++ ) {
            tChoiceList.add("choice [" + pIndex + ":" + i + "]");
        }

        TestObject to = new TestObject();
        to.mBoolValue = ((pIndex % 2) == 0) ? true : false;
        to.mCheckBox = new JCheckBox("ON/OFF [" + pIndex + "]");
        to.mComboBox = new JComboBox( tChoiceList.toArray());
        to.mStrValue = new String("String [ " +pIndex +" ]" );
        to.mLongValue = (Long) (1000000L + pIndex);
        return to;
    }

    public static void main( String[] args ) {
        try {
            javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception ex) {
            System.out.println( ex.getMessage());
        }
        TableTest thisClass = new TableTest();
        thisClass.init();
        thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        thisClass.pack();;
        thisClass.setVisible(true);

        while( true ) {
            try {Thread.currentThread().sleep( 10000 ); }
            catch( InterruptedException e ) {}
        }
    }


    @Override
    public void tableMouseClick(Object pObject, int pRow, int pCol) {
        System.out.println("tableMouseClick row: " + pRow + " col: " +pCol );
    }

    @Override
    public void tableMouseDoubleClick(Object pObject, int pRow, int pCol) {
        System.out.println("tableMouseDoubleClick row: " + pRow + " col: " +pCol );
    }


    public class TestObject
    {
        @TableAttribute( header = "Check Box", column = 0, editable = true, preferedWidth = 70)
        public JCheckBox            mCheckBox;
        @TableAttribute( header = "Combo Box", column = 1, editable = true, preferedWidth = 80)
        public JComboBox<String>    mComboBox;
        @TableAttribute( header = "Str Value", column = 2, editable = true, preferedWidth = 160)
        public String               mStrValue;
        @TableAttribute( header = "Bool Value", column = 3, editable = true, preferedWidth = 60)
        public boolean              mBoolValue;
        @TableAttribute( header = "Long Value", column = 4, editable = true, preferedWidth = 60)
        public long                 mLongValue;
    }

}
