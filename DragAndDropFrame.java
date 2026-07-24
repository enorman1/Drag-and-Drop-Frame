
/**
 * Exemple d'utilisation du mécanisme Glisser/Déposer (Drag & Drop) en Java Swing.
 *
 * Cette application crée une fenêtre capable de recevoir des fichiers déposés
 * par l'utilisateur. Les fichiers transmis sont récupérés via un DropTarget
 * et leurs chemins absolus sont affichés dans la console.
 *
 * Fonctionnalités :
 * - Acceptation d'un ou plusieurs fichiers par glisser-déposer.
 * - Utilisation de DataFlavor.javaFileListFlavor pour récupérer les fichiers.
 * - Gestion des événements de dépôt avec DropTargetListener.
 * 
 * Autre fonctionnalité :
 * - Chargement d'une icône depuis les ressources de l'application.
 *
 * Compatible avec Java 8 et versions supérieures.
 *
 * @author Eric Normandin
 * @date : juillet 2026
 */

//import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

//import java.awt.*;
import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;

import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;

//import java.awt.Toolkit;
//import java.awt.Dimension;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import java.net.URL;


public class DragAndDropFrame extends JFrame {
	
	private static final int FRAME_SIZE_X = 500;  // size x 
	private static final int FRAME_SIZE_Y = 400;  // size y 


	public DragAndDropFrame() {
		setTitle("Glisser-Déposer un ou plusieurs fichiers");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Activer le glisser-déposer pour la JFrame
		new DropTarget(this, new FileDropTargetListener());

		// Ajouter un label pour indiquer à l'utilisateur de déposer un fichier
		JLabel label = new JLabel("Glissez-déposez un fichier ici pour afficher son chemin.", SwingConstants.CENTER);
		add(label, BorderLayout.CENTER);
	}

	// Écouteur pour gérer le glisser-déposer de fichiers
	private static class FileDropTargetListener implements DropTargetListener {

		@Override
		public void dragEnter(DropTargetDragEvent e) {
			// Accepter l'opération de glisser
			//e.acceptDrag(e.getDropAction());
			e.acceptDrag(DnDConstants.ACTION_COPY);
		}

		@Override
		public void dragOver(DropTargetDragEvent e) {
			// Rien à faire ici
		}

		@Override
		public void dropActionChanged(DropTargetDragEvent e) {
			// Rien à faire ici
		}

		@Override
		public void dragExit(DropTargetEvent e) {
			// Rien à faire ici
		}

		@Override
		public void drop(DropTargetDropEvent e) {
			boolean success = false;
			
			if (!e.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
				e.rejectDrop();
				return;
			}
			try {
				// Accepter le dépôt
				e.acceptDrop(DnDConstants.ACTION_COPY);

				// Récupérer la liste des fichiers déposés
				Object transferData = e.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
				@SuppressWarnings("unchecked") // Supprime le warning pour cette ligne
				List<File> droppedFiles = (List<File>) transferData;
				
				//Plusieurs fichiers peuvent être déposés
				for (File file : droppedFiles) {
					if (!file.isDirectory()) {
						System.out.println(file.getAbsolutePath());
					}
				}
				//if (!droppedFiles.isEmpty()) {
				//	for (int i = 0; i < droppedFiles.size(); i++) {
				//		File file = droppedFiles.get(i);
				//		System.out.println(file.getAbsolutePath()); // Chemin complet avec le nom du fichier.
				//		//System.out.println(file.getName());   // Nom du fichier seul
				//		//System.out.println(file.getParent()); // Chemin du répertoire parent sans le nom du fichier.
				//	}
				//}
				
				//e.dropComplete(true);
				success = true;
				
			}
			catch (UnsupportedFlavorException | IOException ex) {
				//e.dropComplete(false);
				ex.printStackTrace();
				JOptionPane.showMessageDialog(
					null,
					"Erreur lors du dépôt du fichier : " + ex.getMessage(),
					"Erreur",
					JOptionPane.ERROR_MESSAGE
				);
			}
			finally {
				e.dropComplete(success);
			}
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			DragAndDropFrame frame = new DragAndDropFrame();
			
			frame.setSize(FRAME_SIZE_X, FRAME_SIZE_Y);
			
			//place la fenetre au centre de l'ecran de l'utilisateur
			//Toolkit tk = Toolkit.getDefaultToolkit();
			//Dimension screenSize = tk.getScreenSize();
			//int screenHeight = screenSize.height;
			//int screenWidth = screenSize.width;
			//frame.setLocation((screenWidth - FRAME_SIZE_X) / 2, (screenHeight - FRAME_SIZE_Y) / 2);
			
			//frame.pack();   // si les layouts sont utilisés
			frame.setLocationRelativeTo(null);
			
			
			// Chargement de l'image depuis les ressources du JAR
			//URL iconUrl = DragAndDropFrame.class.getClassLoader().getResource("DragAndDropFrame.png");
			//frame.setIconImage(tk.getImage(iconUrl));
			//System.out.println(iconUrl);
			Image icon = null;
			try {
				// Assurez-vous que le chemin est correct par rapport a votre structure de projet
				//URL iconURL = DragAndDropFrame.class.getResource("DragAndDropFrame.png");
				URL iconURL = DragAndDropFrame.class.getResource("/DragAndDropFrame.png");
				if (iconURL != null) {
					//Image icon = ImageIO.read(iconURL);
					icon = ImageIO.read(iconURL);
				} else {
					System.err.println("L'icône n'a pas pu être chargée.");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if (icon != null)
				frame.setIconImage(icon);
			
			frame.setVisible(true);
			
		});
	}
}
