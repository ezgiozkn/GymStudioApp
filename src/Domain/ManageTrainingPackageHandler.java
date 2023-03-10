package Domain;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ManageTrainingPackageHandler {
    private Map<Integer, TrainingPackage> packages = this.savePackages();

    public ManageTrainingPackageHandler() {
    }

    public void addPackage(TrainingPackage trainingPackage) {

        String packageID = trainingPackage.getPackageID();
        if (packageID == null) {
            int totalCount = 0;
            Iterator var4 = this.packages.keySet().iterator();

            while (var4.hasNext()) {
                String key = (String) var4.next();
                int mapPackageID = Integer.parseInt(key);
                if (mapPackageID > totalCount) {
                    totalCount = mapPackageID;
                }
            }

            packageID = "" + (totalCount + 1);
        }

        if (this.packages.containsKey(packageID)) {
            System.out.printf("trainingPackage already exist, please retry");
            this.updatePackage(packageID, trainingPackage);
        } else {
            this.packages.put(Integer.parseInt(packageID), trainingPackage);

            try {
                ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream("packages.dat"));
                Throwable var19 = null;

                try {
                    stream.writeObject(this.packages);
                } catch (Throwable var15) {
                    var19 = var15;
                    throw var15;
                } finally {
                    if (stream != null) {
                        if (var19 != null) {
                            try {
                                stream.close();
                            } catch (Throwable var14) {
                                var19.addSuppressed(var14);
                            }
                        } else {
                            stream.close();
                        }
                    }

                }
            } catch (Exception var17) {
                var17.printStackTrace();
                System.exit(0);
            }

            System.out.println("trainingPackage added successfully");
        }
    }

    private void updatePackage(String packageID, TrainingPackage trainingPackage) {
        this.packages.put(Integer.parseInt(packageID), trainingPackage);

        try {
            ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream("packages.dat"));
            Throwable var19 = null;

            try {
                stream.writeObject(this.packages);
            } catch (Throwable var15) {
                var19 = var15;
                throw var15;
            } finally {
                if (stream != null) {
                    if (var19 != null) {
                        try {
                            stream.close();
                        } catch (Throwable var14) {
                            var19.addSuppressed(var14);
                        }
                    } else {
                        stream.close();
                    }
                }

            }
        } catch (Exception var17) {
            var17.printStackTrace();
            System.exit(0);
        }

        System.out.println("trainingPackage added successfully");
    }

    public void deletePackage(int packageID) {
        ObjectOutputStream stream = null;
        try {
            stream = new ObjectOutputStream(new FileOutputStream("packages.dat"));
            this.packages.remove(packageID);
            stream.writeObject(this.packages);
        } catch (Exception e) {
            System.out.println("There was an error during the deletion process");
            e.printStackTrace();
        }
        System.out.println("trainingPackage deleted successfully");
    }

    public Map<Integer, TrainingPackage> savePackages() {
        try {
            ObjectInputStream stream = new ObjectInputStream(new FileInputStream("packages.dat"));
            Throwable var38 = null;

            try {
                this.packages = (Map) stream.readObject();
            } catch (Throwable var32) {
                var38 = var32;
                throw var32;
            } finally {
                if (stream != null) {
                    if (var38 != null) {
                        try {
                            stream.close();
                        } catch (Throwable var31) {
                            var38.addSuppressed(var31);
                        }
                    } else {
                        stream.close();
                    }
                }

            }
        } catch (FileNotFoundException var36) {
            try {
                ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream("packages.dat"));
                Throwable var3 = null;

                try {
                    if (this.packages == null) {
                        this.packages = new HashMap();
                    }

                    stream.writeObject(this.packages);
                } catch (Throwable var30) {
                    var3 = var30;
                    throw var30;
                } finally {
                    if (stream != null) {
                        if (var3 != null) {
                            try {
                                stream.close();
                            } catch (Throwable var29) {
                                var3.addSuppressed(var29);
                            }
                        } else {
                            stream.close();
                        }
                    }
                }
            } catch (Exception var34) {
                var34.printStackTrace();
                System.exit(0);
            }
        } catch (Exception var37) {
            var37.printStackTrace();
            System.exit(0);
        }

        return this.packages;
    }
    public TrainingPackage getPackage(int packageID){
        return (TrainingPackage)this.packages.get(packageID);
    }
}
