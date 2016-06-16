package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import javax.swing.JOptionPane;

import models.*;
import views.html.*;

public class SkillsMatrixController extends Controller {
    
    // Voir la grille de compétences (Elève)
    
    @Security.Authenticated(Secured.class)
    public Result seeSkillsMatrix() {
        return ok(
            showSkillsMatrix.render(
                "Grille de compétences",
                T_mem_status_t.searchStatusByMember(T_member_p.searchIdByLogin(session().get("login"))),
                T_member_p.searchNameByLogin(session().get("login")),
                T_competence_t.find.all(), 
                T_competence_p.find.all(),
                T_competence_l.find.all(),
                12                //T_competence_l.computeMark(1)
        ));
    }
    
    @Security.Authenticated(Secured.class)
    public Result updateSkillMatrix() {
        return ok(
            updateSkillsMatrix.render(
                "Modifier la grille de compétences",
                T_mem_status_t.searchStatusByMember(T_member_p.searchIdByLogin(session().get("login"))),
                T_member_p.searchNameByLogin(session().get("login")),
                form(UpdateSkillsMatrix.class),
                T_competence_t.find.all(), 
                T_competence_p.find.all(),
                T_competence_l.find.all(),
                T_competence_t.skillsInFamily()
        ));
    }
    
     public static class UpdateSkillsMatrix {
        /*public Integer id;
        public Integer skill;
        public Integer level;

            public String validate() {
                if (!T_competence_l.update(id, skill, level)) {
                    return "Grille de compétences invalide !" ;
                }
                return null;
        }*/
    }
    
     @Security.Authenticated(Secured.class)
    public Result modifySkillsMatrix() {
       Form<UpdateSkillsMatrix> modifySkillsMatrixForm = form(UpdateSkillsMatrix.class).bindFromRequest();
        if (modifySkillsMatrixForm.hasErrors()) {
            return badRequest(updateSkillsMatrix.render(
                "Modifier la grille de compétences",
                T_mem_status_t.searchStatusByMember(T_member_p.searchIdByLogin(session().get("login"))),
                T_member_p.searchNameByLogin(session().get("login")),
                modifySkillsMatrixForm,
                T_competence_t.find.all(), 
                T_competence_p.find.all(),
                T_competence_l.find.all(),
                T_competence_t.skillsInFamily()
                ));
        } else {
            return redirect(
                routes.SkillsMatrixController.seeSkillsMatrix()
            );
        }
    }
    
    
    // Modifier le niveau d'une famille de compétences ou d'une compétence

    /*public Result editFamilySkillsLevel(Integer id) {
        T_competence_l.update(id);
        return ok(
            showFamiliesSkills.render(
                "Grille de compétences",
                T_mem_status_t.searchStatusByMember(T_member_p.searchIdByLogin(session().get("login"))),
                T_member_p.searchNameByLogin(session().get("login")),
                T_competence_t.find.all(), 
                T_competence_p.find.all()
            )
        );
    }*/
    
    
    
    
//-------------------------------------------------------------------------------------------------------
    
    
    // Voir les familles de compétences et les compétences (responsable d'APP)
    
    @Security.Authenticated(Secured.class)
    public Result seeFamiliesSkills() {
        return ok(
            showFamiliesSkills.render(
                "Grille de compétences",
                T_mem_status_t.searchStatusByMember(T_member_p.searchIdByLogin(session().get("login"))),
                T_member_p.searchNameByLogin(session().get("login")),
                T_competence_t.find.all(), 
                T_competence_p.find.all()
            )
        );
    }
    
    
//-------------------------------------------------------------------------------------------------------
  
    
    // Ajouter une famille de compétences (responsable d'APP)
    
    @Security.Authenticated(Secured.class)
    public Result addFamilySkills() {
        return ok(
            addFamiliesSkills.render(
                "Ajouter une famille de compétences",
                T_mem_status_t.searchStatusByMember(T_member_p.searchIdByLogin(session().get("login"))),
                T_member_p.searchNameByLogin(session().get("login")),
                form(AddFamSkills.class)
            )
        ); 
    }
    
     public static class AddFamSkills {
        public String name;
        public Integer coeff;
        public String description;
        
        public String validate() {
            if (T_competence_t.create(name, coeff, description) == null) {
                return "Famille de compétence invalide : " ;
            }
            return null;
        }
    }
    
     @Security.Authenticated(Secured.class)
    public Result createFamilySkills() {
        Form<AddFamSkills> addfamilySkillsForm = form(AddFamSkills.class).bindFromRequest();
        if (addfamilySkillsForm.hasErrors()) {
            return badRequest(addFamiliesSkills.render(
                "Ajouter une famille de compétences",
                T_mem_status_t.searchStatusByMember(T_member_p.searchIdByLogin(session().get("login"))),
                T_member_p.searchNameByLogin(session().get("login")),
                addfamilySkillsForm
            ));
        } else {
            return redirect(
                routes.SkillsMatrixController.seeFamiliesSkills()
            );
        }
    }
    
    
//-------------------------------------------------------------------------------------------------------
    
    
    // Modifier une famille de compétences (responsable d'APP)
    
    @Security.Authenticated(Secured.class)
    public Result editFamilySkills(Integer id) {
        return ok(
            updateFamiliesSkills.render(
                "Modifier une famille de compétencess",
                T_mem_status_t.searchStatusByMember(T_member_p.searchIdByLogin(session().get("login"))),
                T_member_p.searchNameByLogin(session().get("login")),
                form(UpdateFamilySkills.class), 
                T_competence_t.find.byId(id)
            )
        );
    }
    
    public static class UpdateFamilySkills {
        public Integer id;
        public String name;
        public Integer coeff;
        public String description;
        
        public String validate() {
            if (!T_competence_t.update(id, name, coeff, description)) {
                return "Famille de compétence invalide";
            }
            return null;
        }
    }
    
    @Security.Authenticated(Secured.class)
    public Result updateFamilySkills(Integer id) {
        Form<UpdateFamilySkills> updatefamilySkillsForm = form(UpdateFamilySkills.class).bindFromRequest();
        if (updatefamilySkillsForm.hasErrors()) {
            return badRequest(updateFamiliesSkills.render(
                "Modifier une famille de compétences",
                T_mem_status_t.searchStatusByMember(T_member_p.searchIdByLogin(session().get("login"))),
                T_member_p.searchNameByLogin(session().get("login")),
                updatefamilySkillsForm, 
                T_competence_t.find.byId(id)
            ));
        } else {
            return redirect(
                routes.SkillsMatrixController.seeFamiliesSkills()
            );
        }
    }
    
    
//-------------------------------------------------------------------------------------------------------
    
    
    // Supprimer une famille de compétences (responsable d'APP)
    
     @Security.Authenticated(Secured.class)
    public Result deleteFamilySkills(Integer id) {
        T_competence_t.delete(id);
        T_competence_p.deleteWithFamilies(id);
        return ok(
            showFamiliesSkills.render(
                "Grille de compétences",
                T_mem_status_t.searchStatusByMember(T_member_p.searchIdByLogin(session().get("login"))),
                T_member_p.searchNameByLogin(session().get("login")),
                T_competence_t.find.all(), 
                T_competence_p.find.all()
            )
        );
    }
    
    
//-------------------------------------------------------------------------------------------------------


    // Ajouter une compétence à une famille (responsable d'APP)
    
    @Security.Authenticated(Secured.class)
    public Result addSkill(Integer id) {
        return ok(
            addSkills.render(
                "Ajouter une compétence",
                T_mem_status_t.searchStatusByMember(T_member_p.searchIdByLogin(session().get("login"))),
                T_member_p.searchNameByLogin(session().get("login")),
                form(AddSkills.class), 
                T_competence_t.find.byId(id)
            )
        ); 
    }
    
    public static class AddSkills {
        public String name;
        public String description;
        public Integer type;
        
        public String validate() {
            if (T_competence_p.create(name, description, type) == null) {
                return "Compétence invalide";
            }
            return null;
        }
    }
    
    @Security.Authenticated(Secured.class)
    public Result createSkill(Integer id) {
        Form<AddSkills> skillForm = form(AddSkills.class).bindFromRequest();
        if (skillForm.hasErrors()) {
            return badRequest(addSkills.render(
                "Ajouter une compétence",
                T_mem_status_t.searchStatusByMember(T_member_p.searchIdByLogin(session().get("login"))),
                T_member_p.searchNameByLogin(session().get("login")),
                skillForm, 
                T_competence_t.find.byId(id)
            ));
        } else {
            return redirect(
                routes.SkillsMatrixController.seeFamiliesSkills()
            );
        }
    }
    
    
//-------------------------------------------------------------------------------------------------------


        // Modifier une compétence (responsable d'APP)
    
    @Security.Authenticated(Secured.class)
    public Result editSkill(Integer id) {
        return ok(
            updateSkills.render(
                "Modifier une compétence",
                T_mem_status_t.searchStatusByMember(T_member_p.searchIdByLogin(session().get("login"))),
                T_member_p.searchNameByLogin(session().get("login")),
                form(UpdateSkills.class), 
                T_competence_p.find.byId(id)
            )
        );
    }
    
    public static class UpdateSkills {
        public Integer id;
        public String name;
        public String description;
        
        public String validate() {
            if (!T_competence_p.update(id, name, description)) {
                return "Compétence invalide";
            }
            return null;
        }
    }
    
    @Security.Authenticated(Secured.class)
    public Result updateSkill(Integer id) {
        Form<UpdateSkills> skillForm = form(UpdateSkills.class).bindFromRequest();
        if (skillForm.hasErrors()) {
            return badRequest(updateSkills.render(
                "Modifier une compétence",
                T_mem_status_t.searchStatusByMember(T_member_p.searchIdByLogin(session().get("login"))),
                T_member_p.searchNameByLogin(session().get("login")),
                skillForm, 
                T_competence_p.find.byId(id)
            ));
        } else {
            return redirect(
                routes.SkillsMatrixController.seeFamiliesSkills()
            );
        }
    } 
    
    
//-------------------------------------------------------------------------------------------------------


    // Supprimer une compétence (responsable d'APP)
    
     @Security.Authenticated(Secured.class)
    public Result deleteSkill(Integer id) {
        /*Object[] options = {"Confirmer", "Annuler"};
        int n = JOptionPane.showOptionDialog(AppliJOptionPane.this, "Etes-vous sûrs de vouloir supprimer cette compétence ?", "Suppression", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
        null,     //do not use a custom Icon
        options,  //the titles of buttons
        options[0]); //default button title*/
        //JOptionPane.showMessageDialog(frame, "A basic JOptionPane message dialog");
        T_competence_p.delete(id);
        return ok(
            showFamiliesSkills.render(
                "Grille de compétences",
                T_mem_status_t.searchStatusByMember(T_member_p.searchIdByLogin(session().get("login"))),
                T_member_p.searchNameByLogin(session().get("login")),
                T_competence_t.find.all(), 
                T_competence_p.find.all()
                )
        );
    }

}